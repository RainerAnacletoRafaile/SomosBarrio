package com.santiago.somosbarrio.data.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.santiago.somosbarrio.data.session.SessionManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Cliente HTTP básico para consumir la API REST desde Java.
 *
 * Ejemplo:
 * ApiClient api = new ApiClient(this);
 * api.post("auth/login", datos, new ApiCallback() { ... });
 *
 * Los nombres de endpoints y el formato JSON se definirán con el equipo backend.
 */
public class ApiClient {

    private static final ExecutorService executor = Executors.newFixedThreadPool(3);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final SessionManager sessionManager;

    public ApiClient(Context context) {
        sessionManager = new SessionManager(context);
    }

    public void get(String endpoint, ApiCallback callback) {
        request("GET", endpoint, null, callback);
    }

    public void post(String endpoint, JSONObject body, ApiCallback callback) {
        request("POST", endpoint, body, callback);
    }

    public void put(String endpoint, JSONObject body, ApiCallback callback) {
        request("PUT", endpoint, body, callback);
    }

    public void delete(String endpoint, ApiCallback callback) {
        request("DELETE", endpoint, null, callback);
    }

    private void request(String method, String endpoint, JSONObject body,
                         ApiCallback callback) {
        executor.execute(() -> {
            HttpURLConnection connection = null;
            int statusCode = -1;

            try {
                String cleanEndpoint = endpoint.startsWith("/")
                        ? endpoint.substring(1) : endpoint;
                URL url = new URL(ApiConfig.BASE_URL + cleanEndpoint);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.setConnectTimeout(ApiConfig.CONNECT_TIMEOUT_MS);
                connection.setReadTimeout(ApiConfig.READ_TIMEOUT_MS);
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                String token = sessionManager.getAccessToken();
                if (token != null && !token.trim().isEmpty()) {
                    connection.setRequestProperty("Authorization", "Bearer " + token);
                }

                if (body != null && ("POST".equals(method) || "PUT".equals(method)
                        || "PATCH".equals(method))) {
                    connection.setDoOutput(true);
                    byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
                    try (OutputStream output = connection.getOutputStream()) {
                        output.write(data);
                    }
                }

                statusCode = connection.getResponseCode();
                InputStream stream = statusCode >= 200 && statusCode < 400
                        ? connection.getInputStream() : connection.getErrorStream();
                String responseText = readStream(stream);
                JSONObject response = responseText.isEmpty()
                        ? new JSONObject() : new JSONObject(responseText);

                if (statusCode >= 200 && statusCode < 300) {
                    final int code = statusCode;
                    mainHandler.post(() -> callback.onSuccess(code, response));
                } else {
                    final int code = statusCode;
                    final String message = response.optString("message",
                            "La solicitud no pudo completarse.");
                    mainHandler.post(() -> callback.onError(code, message));
                }

            } catch (Exception exception) {
                final int code = statusCode;
                final String message = exception.getMessage() == null
                        ? "No se pudo conectar con el servidor." : exception.getMessage();
                mainHandler.post(() -> callback.onError(code, message));
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }

    private String readStream(InputStream stream) throws Exception {
        if (stream == null) return "";
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
