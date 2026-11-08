package com.santiago.somosbarrio;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public abstract class BaseActivity extends AppCompatActivity {
 protected void mensaje(String texto){ Toast.makeText(this,texto,Toast.LENGTH_LONG).show(); }
 protected boolean requerido(android.widget.EditText campo,String etiqueta){
  String v=campo.getText().toString().trim();
  if(v.isEmpty()){campo.setError(etiqueta+" es obligatorio");campo.requestFocus();return false;} return true;
 }
 protected void abrir(Class<?> destino){startActivity(new android.content.Intent(this,destino));}
}