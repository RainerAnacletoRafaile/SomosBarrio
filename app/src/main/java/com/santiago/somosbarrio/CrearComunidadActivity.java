package com.santiago.somosbarrio;
import android.os.Bundle; import android.text.Editable; import android.text.TextWatcher; import android.widget.*;
public class CrearComunidadActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_presidente);
  EditText nombre=findViewById(R.id.etNombreComunidad), descripcion=findViewById(R.id.etDescripcionComunidad), ubicacion=findViewById(R.id.etUbicacionComunidad);
  TextView contador=findViewById(R.id.tvContador);
  descripcion.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int st,int c,int a){} public void onTextChanged(CharSequence s,int st,int before,int count){contador.setText(s.length()+"/100");} public void afterTextChanged(Editable e){}});
  findViewById(R.id.btnCrearComunidad).setOnClickListener(v->{
   if(!requerido(nombre,"Nombre de comunidad")||!requerido(descripcion,"Descripción")||!requerido(ubicacion,"Ubicación"))return;
   mensaje("Formulario validado. La creación real requiere el endpoint del backend.");
  });
  findViewById(R.id.tvVolverPresidente).setOnClickListener(v->finish());
 }
}