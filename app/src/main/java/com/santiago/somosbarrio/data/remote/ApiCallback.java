package com.santiago.somosbarrio.data.remote;

import org.json.JSONObject;

/**
 * Resultado de una petición HTTP.
 * Los callbacks se ejecutan en el hilo principal.
 */
public interface ApiCallback {
    void onSuccess(int statusCode, JSONObject response);
    void onError(int statusCode, String message);
}
