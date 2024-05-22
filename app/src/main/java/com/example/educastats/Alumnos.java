package com.example.educastats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.educastats.databinding.ActivityMyappBinding;
import com.google.android.material.navigation.NavigationView;

public class Alumnos extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edtDoc;
    private TextView tvName, tvPrescolar, tvPrimero;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMyappBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alumnos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtDoc = (EditText) findViewById(R.id.edtDoc);

        tvName = (TextView) findViewById(R.id.tvName);
        tvPrescolar = (TextView) findViewById(R.id.tvPrescolar);
        tvPrimero = (TextView) findViewById(R.id.tvPrimero);

    }
    public void AddStudent(View view) {
        Intent i = new Intent(this, Matricula.class);
        startActivity(i);
    }
//    public void Buscar(View view){
//        setContentView(R.layout.activity_buscar_alumno);
//    }
    public void Prescolar(View view) {
        Intent i = new Intent(this, lisAlumnos.class);
        i.putExtra("grado", tvPrescolar.getText().toString());
        startActivity(i);
    }
    public void Primero(View view) {
        Intent i = new Intent(this, lisAlumnos.class);
        i.putExtra("grado", tvPrimero.getText().toString());
        startActivity(i);
    }

//    public void BusacarAlumno(View view)
//    {
//        EducaStatsDB conexion = new EducaStatsDB(this, "EducaDB", null, 1);
//        SQLiteDatabase BaseDatos = conexion.getWritableDatabase();
//
//        String Documento = edtDoc.getText().toString();
//
//        if (!Documento.isEmpty())
//        {
//            Cursor fila=BaseDatos.rawQuery("select PrimerNombre from Alummos where documentoId="+Documento,null);
//            tvName.setText(fila.getString(0));
//            BaseDatos.close();
//        }
//    }

    //region OnClick

    //endregion


}