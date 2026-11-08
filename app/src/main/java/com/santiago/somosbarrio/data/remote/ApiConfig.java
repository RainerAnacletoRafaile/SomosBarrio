package com.santiago.somosbarrio.data.remote;

/**
 * Configuración central de la API REST.
 *
 * Reemplazar BASE_URL cuando Arnold y Juan compartan la URL del backend.
 * La URL debe terminar en / si los endpoints se concatenan directamente.
 */
public final class ApiConfig {

    // TODO: reemplazar por la URL real de la API REST.
    // Ejemplo: https://api.somosbarrio.com/
    public static final String BASE_URL = "https://TU-URL-API/";

    public static final int CONNECT_TIMEOUT_MS = 15000;
    public static final int READ_TIMEOUT_MS = 15000;

    private ApiConfig() {
        // Evita instanciar esta clase.
    }
}
