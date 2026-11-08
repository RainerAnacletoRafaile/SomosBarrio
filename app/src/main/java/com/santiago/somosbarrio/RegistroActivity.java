package com.santiago.somosbarrio;
import android.os.Bundle; import android.widget.*;
public class RegistroActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_registro_vecino);
  EditText nombres=findViewById(R.id.etNombres), apellidos=findViewById(R.id.etApellidos), telefono=findViewById(R.id.etTelefonoRegistro), clave=findViewById(R.id.etPasswordRegistro);
  findViewById(R.id.btnCrearCuenta).setOnClickListener(v->{
   if(!requerido(nombres,"Nombres")||!requerido(apellidos,"Apellidos")||!requerido(telefono,"Celular")||!requerido(clave,"Contraseña"))return;
   if(telefono.getText().toString().trim().length()!=9){telefono.setError("Ingresa un celular de 9 dígitos");return;}
   if(clave.getText().toString().length()<8){clave.setError("Usa al menos 8 caracteres");return;}
   mensaje("Datos validados. Falta conectar el endpoint de registro del backend.");
  });
  findViewById(R.id.tvIrLogin).setOnClickListener(v->finish());
  findViewById(R.id.tvVolverRegistro).setOnClickListener(v->finish());
 }
}