package com.santiago.somosbarrio;
import android.os.Bundle; import android.text.Editable; import android.text.TextWatcher; import android.widget.*; import java.util.Locale;
public class CrearComunidadActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_presidente);
  EditText nombre=findViewById(R.id.etNombreComunidad), descripcion=findViewById(R.id.etDescripcionComunidad), ubicacion=findViewById(R.id.etUbicacionComunidad);
  TextView contador=findViewById(R.id.tvContador);
  descripcion.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int st,int c,int a){} public void onTextChanged(CharSequence s,int st,int before,int count){contador.setText(s.length()+"/100");} public void afterTextChanged(Editable e){}});
  findViewById(R.id.btnCrearComunidad).setOnClickListener(v->{
   if(!requerido(nombre,"Nombre de comunidad")||!requerido(descripcion,"Descripción")||!requerido(ubicacion,"Ubicación"))return;
   String codigo=("SB"+Integer.toString((int)(Math.random()*900000+100000))).toUpperCase(Locale.ROOT);
   android.content.Intent intent=new android.content.Intent(this,ComunidadCreadaActivity.class);
   intent.putExtra("nombre_comunidad",nombre.getText().toString().trim());
   intent.putExtra("descripcion",descripcion.getText().toString().trim());
   intent.putExtra("ubicacion",ubicacion.getText().toString().trim());
   intent.putExtra("codigo",codigo);
   mensaje("Comunidad creada en modo demostración. Código temporal, no registrado en el servidor.");
   startActivity(intent);
  });
  findViewById(R.id.tvVolverPresidente).setOnClickListener(v->finish());
 }
}