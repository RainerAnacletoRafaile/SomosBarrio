# Integración Android - API REST SomosBarrio

## Estado
La aplicación Android tiene una base Java para consumir una API REST. Todavía no se han fijado endpoints porque Arnold y Juan están desarrollando el backend con Supabase.

## Componentes agregados
- `data/remote/ApiConfig.java`: URL base y tiempos de espera.
- `data/remote/ApiClient.java`: peticiones GET, POST, PUT y DELETE en segundo plano.
- `data/remote/ApiCallback.java`: respuestas y errores en el hilo principal.
- `data/session/SessionManager.java`: almacenamiento local del token de acceso.

## Pendiente del equipo backend
Compartir y acordar:
1. URL base de desarrollo y producción.
2. Endpoints y métodos HTTP.
3. JSON de solicitud y respuesta de cada endpoint.
4. Esquema de autenticación y nombre del campo del token.
5. Códigos HTTP y formato estándar de errores.
6. Reglas CORS no aplican a Android nativo; sí deben habilitarse HTTPS y acceso de red desde el dispositivo.

## Configuración
En `ApiConfig.java`, reemplazar `https://TU-URL-API/` por la URL real del backend. No guardar claves privadas de Supabase ni secretos administrativos en la aplicación Android.

## Ejemplo de uso desde una Activity
```java
JSONObject body = new JSONObject();
body.put("correo", correo);
body.put("password", password);

ApiClient api = new ApiClient(this);
api.post("ruta/login", body, new ApiCallback() {
    @Override
    public void onSuccess(int statusCode, JSONObject response) {
        // Leer respuesta y continuar la navegación.
        // Guardar el token solo cuando el backend confirme su nombre/formato.
    }

    @Override
    public void onError(int statusCode, String message) {
        // Mostrar el error al usuario.
    }
});
```

## Importante
No implementar todavía login, registro ni comunidades contra rutas inventadas. Primero se acuerda el contrato API con Arnold y Juan; después se conectan los formularios reales.
