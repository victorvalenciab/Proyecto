package com.example.educastats;

import static com.example.educastats.R.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    private TextView tvUsuario, tvContrasena, tvOlvidarContrasena;
    private EditText edtUsuario, edtContrasena;
    boolean passwordVisable;
    private String[] usernames = {"usuario1", "usuario2"};
    private String[] passwords = {"password1", "password2"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // TextView
        tvUsuario = (TextView) findViewById(R.id.tvUsuario);
        tvContrasena = (TextView) findViewById(R.id.tvContrasena);
        tvOlvidarContrasena = (TextView) findViewById(R.id.tvOlvidarContrasena);

        // EditText
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtContrasena = (EditText) findViewById(R.id.edtContrasena);


        // Llamo a los metodos(Eventos)
        //region Metodos
        EventoUsuario();
        EventoContrasena();
        VerContrasena();
        //endregion
    }

    //region Evento OnClick

    public void Acceder(View view)
    {

          String user = edtUsuario.getText().toString();
          String pass = edtContrasena.getText().toString();


          if (isValidCredentials(user, pass)) {
            // Credenciales válidas
              Intent i = new Intent(this, myapp.class);
               startActivity(i);
            Toast.makeText(MainActivity.this, "Acceso exitoso", Toast.LENGTH_SHORT).show();
            // Aquí puedes dirigir al usuario a otra actividad o realizar otras acciones.
              
          } else {
            // Credenciales inválidas
            Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
          }


    }

    private boolean isValidCredentials(String user, String pass) {
        for (int i = 0; i < usernames.length; i++) {
            if (user.equals(usernames[i]) && pass.equals(passwords[i])) {
                return true;
            }
        }
        return false;
    }
    public void OlvidasteContrasena(View view) // TextView Olvida contraseña
    {

        //Intent i = new Intent(this, OlvidarContrasena.class);
        //startActivity(i);
    }
    //endregion
    //region Eventos Enter-Leave Usaurio Contraseña
    private void EventoUsuario()
    {
        // Usuario
        edtUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String EnterUsuario = edtUsuario.getText().toString();
                if (hasFocus) { // ENTER
                    // Enter Usuario
                    if (EnterUsuario.equals("Usuario")) {
                        edtUsuario.setText("");
                        edtUsuario.setTextColor(getResources().getColor(color.white));
                        tvUsuario.setVisibility(View.VISIBLE);
                    }
                } else if (EnterUsuario.equals(""))//LEAVE
                {
                    edtUsuario.setText("Usuario");
                    edtUsuario.setTextColor(getResources().getColor(color.colorTextLeave));
                    tvUsuario.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    private void EventoContrasena()
    {
        // Contraseña
        edtContrasena.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String EnterContraseña =  edtContrasena.getHint().toString();
                if (hasFocus) { // ENTER
                    // Enter Contraseña
                    if (EnterContraseña.equals("Contraseña")) {
                        edtContrasena.setHint("");
                        edtContrasena.setTextColor(getResources().getColor(color.white));
                        tvContrasena.setVisibility(View.VISIBLE);
                    }
                } else if(EnterContraseña.equals("")) //LEAVE
                {
                    edtContrasena.setHint("Contraseña");
                    edtContrasena.setTextColor(getResources().getColor(color.colorTextLeave));
                    tvContrasena.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    //endregion
    //region Funciones
    @SuppressLint("ClickableViewAccessibility")
    private void VerContrasena()
    {

        edtContrasena.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=edtContrasena.getRight()-edtContrasena.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection= edtContrasena.getSelectionEnd();
                        if(passwordVisable)
                        {
                            // set drawable img here
                            edtContrasena.setCompoundDrawablesRelativeWithIntrinsicBounds
                                    (drawable.baseline_lock_24,0,R.drawable.baseline_visibility_off_24, 0);

                            // for hide password
                            edtContrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisable=false;
                        }else
                        {
                            // set drawable img here
                            edtContrasena.setCompoundDrawablesRelativeWithIntrinsicBounds
                                    (drawable.baseline_lock_24,0,R.drawable.baseline_visibility_24, 0);
                            // for show password
                            edtContrasena.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisable=true;
                        }
                        edtContrasena.setSelection(selection);
                        return  true;
                    }
                }
                return false;
            }
        });
    }

    //endregion
}