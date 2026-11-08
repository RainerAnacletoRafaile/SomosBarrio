package com.santiago.somosbarrio;
import android.os.Bundle; import android.content.ClipData; import android.content.ClipboardManager; import android.content.Context; import android.widget.TextView;
public class ComunidadCreadaActivity extends BaseActivity {
 @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_comunidad_creada);
  String nombre=getIntent().getStringExtra("nombre_comunidad"), descripcion=getIntent().getStringExtra("descripcion"), ubicacion=getIntent().getStringExtra("ubicacion"), codigo=getIntent().getStringExtra("codigo");
  set(R.id.tvNombreComunidadCreada,nombre);set(R.id.tvDescripcionComunidad,descripcion);set(R.id.tvUbicacionComunidadCreada,ubicacion);set(R.id.tvCodigoInvitacion,codigo);
  findViewById(R.id.btnCopiarCodigo).setOnClickListener(v->{if(codigo==null||codigo.isEmpty()){mensaje("Todavía no hay un código asignado por el servidor.");return;} ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);cm.setPrimaryClip(ClipData.newPlainText("Código de comunidad",codigo));mensaje("Código copiado.");});
  findViewById(R.id.btnIrMiComunidad).setOnClickListener(v->mensaje("La pantalla de comunidad se implementará con el siguiente módulo."));
 }
 private void set(int id,String value){if(value!=null)((TextView)findViewById(id)).setText(value);}
}