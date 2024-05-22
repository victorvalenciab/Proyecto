package com.example.educastats;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notas extends Fragment {
    private EditText Nota1, Nota2, id_estudiante;

    private Button Calificar, Consultar;

    public Notas() {
        // Constructor vacío requerido por los fragmentos
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_notas, container, false);

        // Inicializar vistas
        Nota1 = view.findViewById(R.id.Nota1);
        Nota2 = view.findViewById(R.id.Nota2);
        id_estudiante = view.findViewById(R.id.id_estudiante);

        // Obtener el botón Registrar del layout
        Calificar = view.findViewById(R.id.Calificar);
        Consultar = view.findViewById(R.id.Consultar);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarnota(v);
            }
        });

        // Configurar el clic del botón Registrar
        Calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método calnota() cuando se haga clic en el botón
                calnota(v);
            }
        });

        return view;
    }

    public void buscarnota(View view) {
        mostrarNotas();
    }

    public void calnota(View view) {
        // Este método se llamará cuando se haga clic en el botón Registrar
        Toast.makeText(getContext(), "Sacando calificacion", Toast.LENGTH_LONG).show();
        registrarNota();
    }

    private void registrarNota() {
        // Obtener las notas ingresadas por el usuario
        String N1 = Nota1.getText().toString().trim();
        String N2 = Nota2.getText().toString().trim();
        String idEstudiante = id_estudiante.getText().toString().trim();

        // Verificar si los campos están vacíos
        if (N1.isEmpty() || N2.isEmpty()) {
            Toast.makeText(getContext(), "Se deben ingresar ambas notas", Toast.LENGTH_LONG).show();
            return;
        }

        // Convertir las notas a valores enteros
        int nota1 = Integer.parseInt(N1);
        int nota2 = Integer.parseInt(N2);
        int alumnoId = Integer.parseInt(idEstudiante);

        // Insertar las notas en la base de datos
        EducaStatsDB educaDB = new EducaStatsDB(getContext(), "EducaDB", null, 1);
        SQLiteDatabase db = educaDB.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("alumno_id", alumnoId);
        values1.put("nota", nota1);
        long newRowId1 = db.insert("Notas", null, values1);

        ContentValues values2 = new ContentValues();
        values2.put("alumno_id", alumnoId);
        values2.put("nota", nota2);
        long newRowId2 = db.insert("Notas", null, values2);

        // Comprobar si la inserción fue exitosa
        if (newRowId1 != -1 && newRowId2 != -1) {
            Toast.makeText(getContext(), "Notas almacenadas exitosamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al almacenar las notas", Toast.LENGTH_LONG).show();
        }

        // Limpiar los campos de entrada
        Nota1.setText("");
        Nota2.setText("");
        id_estudiante.setText("");
    }

    private void mostrarNotas() {
        // Obtener el ID del estudiante
        String idEstudiante = id_estudiante.getText().toString().trim();
        int alumnoId = Integer.parseInt(idEstudiante);

        // Consultar la base de datos para obtener las notas del estudiante
        EducaStatsDB educaDB = new EducaStatsDB(getContext(), "EducaDB", null, 1);
        SQLiteDatabase db = educaDB.getReadableDatabase();

        // Definir la consulta SQL para obtener las notas del estudiante
        String query = "SELECT nota FROM Notas WHERE alumno_id = ?";

        // Ejecutar la consulta
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(alumnoId)});

        // Verificar si se obtuvieron resultados
        if (cursor != null && cursor.moveToFirst()) {
            // Mostrar el TextView que contendrá las notas
            TextView textViewNotas = getView().findViewById(R.id.textViewNotas);
            textViewNotas.setVisibility(View.VISIBLE);

            // Construir un StringBuilder para almacenar las notas
            StringBuilder notasStringBuilder = new StringBuilder();

            // Iterar sobre el cursor para obtener todas las notas
            do {
                // Obtener la nota actual

                int nota = cursor.getInt(cursor.getColumnIndex("nota"));

                // Agregar la nota al StringBuilder
                notasStringBuilder.append("Nota: ").append(nota).append("\n");
            } while (cursor.moveToNext());

            // Mostrar las notas en el TextView
            textViewNotas.setText(notasStringBuilder.toString());

            // Cerrar el cursor
            cursor.close();
        } else {
            // Si no hay notas para mostrar, mostrar un mensaje de que no hay notas
            Toast.makeText(getContext(), "No hay notas para mostrar", Toast.LENGTH_SHORT).show();
        }
    }
}