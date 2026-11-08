package com.santiago.somosbarrio;
import android.os.Bundle;
public class AccesoActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_acceso);
  findViewById(R.id.cardCrearComunidad).setOnClickListener(v->abrir(CrearComunidadActivity.class));
  findViewById(R.id.cardUnirseComunidad).setOnClickListener(v->abrir(UnirseComunidadActivity.class));
 }
}