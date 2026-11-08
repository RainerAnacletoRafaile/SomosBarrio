package com.santiago.somosbarrio.data.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Guarda localmente el token de sesión entregado por la API.
 * No almacena contraseñas.
 */
public class SessionManager {

    private static final String PREF_NAME = "somos_barrio_session";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        preferences = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveAccessToken(String token) {
        preferences.edit().putString(KEY_ACCESS_TOKEN, token).apply();
    }

    public String getAccessToken() {
        return preferences.getString(KEY_ACCESS_TOKEN, null);
    }

    public boolean hasSession() {
        String token = getAccessToken();
        return token != null && !token.trim().isEmpty();
    }

    public void clearSession() {
        preferences.edit().remove(KEY_ACCESS_TOKEN).apply();
    }
}
