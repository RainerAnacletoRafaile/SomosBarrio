package com.santiago.somosbarrio;
import android.os.Bundle; import android.widget.*;
public class LoginActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_login);
  EditText telefono=findViewById(R.id.etTelefonoLogin), clave=findViewById(R.id.etPasswordLogin);
  findViewById(R.id.btnIniciarSesion).setOnClickListener(v->{
   if(!requerido(telefono,"Celular")||!requerido(clave,"Contraseña"))return;
   if(telefono.getText().toString().trim().length()!=9){telefono.setError("Ingresa un celular de 9 dígitos");return;}
   mensaje("Formulario validado. La autenticación se conectará cuando el equipo publique el endpoint.");
  });
  findViewById(R.id.btnIrRegistro).setOnClickListener(v->abrir(RegistroActivity.class));
 }
}