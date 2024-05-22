package com.example.educastats;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.educastats.R.*;

public class Matricula extends AppCompatActivity {

    private Spinner spListGrados;
    private EditText edtPrimerNombre, edtSegundoNombre, edtPrimerApellido, edtSegudnoApellido, edtDocumento,
            edtEdad, edtFechaNacimiento, edtGmail, edtUsaurioC, edtContarseñaC;
    String grado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(com.example.educastats.R.layout.activity_matricula);

        edtPrimerNombre = (EditText) findViewById(R.id.edtPrimerNombre);
        edtSegundoNombre = (EditText) findViewById(R.id.edtSegundoNombre);
        edtPrimerApellido = (EditText) findViewById(R.id.edtPrimerApellido);
        edtSegudnoApellido = (EditText) findViewById(R.id.edtSegundoApellido);
        edtDocumento = (EditText) findViewById(R.id.edtDocumento);
        edtEdad = (EditText) findViewById(R.id.edtEdad);
        spListGrados = (Spinner) findViewById(R.id.spListGrados);


        spListGrados = (Spinner) findViewById(id.spListGrados);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array.Grados,
                layout.disainer_spinner);
        spListGrados.setAdapter(adapter);

//        spListGrados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), "Grado: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void Matricular(View view)
    {
        EducaStatsDB database = new EducaStatsDB(this, "EducaDB", null, 1);
        SQLiteDatabase BaseDatos = database.getWritableDatabase();
        // Obtener la posición del ítem seleccionado en el Spinner
        int position = spListGrados.getSelectedItemPosition();

        String PrimerNombre = edtPrimerNombre.getText().toString();
        String SegudnoNombre = edtSegundoNombre.getText().toString();
        String PrimerApellido = edtPrimerApellido.getText().toString();
        String SegudnoApellido = edtSegudnoApellido.getText().toString();
        String Documento = edtDocumento.getText().toString();
        String Edad = edtEdad.getText().toString();
        String Grado = spListGrados.getItemAtPosition(position).toString();


        if (!PrimerNombre.isEmpty() && !SegudnoNombre.isEmpty() && !PrimerApellido.isEmpty() && !SegudnoApellido.isEmpty() && !Documento.isEmpty()
         && !Edad.isEmpty() && !Grado.isEmpty() )
        {
            ContentValues registro = new ContentValues();

            registro.put("documentoId", Documento);
            registro.put("PrimerNombre", PrimerNombre);
            registro.put("SegundoNombre", SegudnoNombre);
            registro.put("PrimerApellido", PrimerApellido);
            registro.put("SegundoApellido", SegudnoApellido);
            registro.put("Edad", Edad);
            registro.put("Grado", Grado);

            BaseDatos.insert("Alumnos", null, registro);
            BaseDatos.close();
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Se debe Ingresar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
    //endregion
    //region Metodo-BaseDatos

    //endregion
}
