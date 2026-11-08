package com.santiago.somosbarrio;
import android.os.Bundle; import android.widget.*;
public class UnirseComunidadActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_unirse_comunidad);
  EditText codigo=findViewById(R.id.etCodigoInvitacion);
  findViewById(R.id.btnSolicitarIngreso).setOnClickListener(v->{
   if(!requerido(codigo,"Código de invitación"))return;
   String valor=codigo.getText().toString().trim().toUpperCase();
   if(valor.length()<5){codigo.setError("Revisa el código de invitación");return;}
   mensaje("Código validado. El envío de solicitud se conectará con el endpoint del backend.");
  });
  findViewById(R.id.btnVolver).setOnClickListener(v->finish());
 }
}