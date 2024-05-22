package com.example.educastats;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class lisAlumnos extends AppCompatActivity {
    List<ListElement> elements;
    RecyclerView ListAlumnos;
    private TextView tvTitleAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lis_alumnos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvTitleAlumnos = (TextView) findViewById(R.id.tvTitleAlumnos);
        ListAlumnos = (RecyclerView) findViewById(R.id.ListAlumnos);
        elements = new ArrayList<>();
        ListAlumnos.setLayoutManager(new LinearLayoutManager(this));



        init();


    }


    public void init() {
        // Rebibir el grado en el que esta
        String curso = getIntent().getStringExtra("grado");
        Toast.makeText(this, curso, Toast.LENGTH_LONG).show();;

        tvTitleAlumnos.setText(curso);

         EducaStatsDB data = new EducaStatsDB(this, "EducaDB", null, 1);
         SQLiteDatabase BaseDatos = data.getReadableDatabase();


        elements.add(new ListElement("#000000", "", "Alonzo Sanchez", curso));
        elements.add(new ListElement("#254567", "Victor Manuel ", "Betancur Valencia", curso));
        elements.add(new ListElement("#FF5733", "Juan Manuel", "Hernandez", curso));
        elements.add(new ListElement("#96FF33", "Willy Manuel", "Alonzo Sanchez", curso));
        elements.add(new ListElement("#556448", "Paula Manuel", "Hernandez", curso));
        elements.add(new ListElement("#484864", "Sebastian Manuel", "Alonzo Sanchez", curso));

        // Crear una nueva lista para almacenar los elementos filtrados
        ArrayList<ListElement> filteredElements = new ArrayList<>();

        // Iterar sobre cada elemento en la lista original
        for (ListElement element : elements) {
            // Verificar si el nombre del elemento cumple con cierta condición
            if (element.getgrado().equals(curso)) {
                // Agregar el elemento a la lista de elementos filtrados
                filteredElements.add(element);
            }
        }

        // Crear un adaptador con la lista de elementos filtrados
        ListAdapter listAdapter = new ListAdapter(filteredElements, this);

        RecyclerView recyclerView = findViewById(R.id.ListAlumnos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }


}