package com.example.educastats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EducaStatsDB  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static  final String DATABASE_NAME = "Educa.db";
    public static final String TABLE_ALUMNOS = "Alumnos";

    public EducaStatsDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase EducaDB) {
        EducaDB.execSQL(" CREATE TABLE Alumnos(documentoId int PRIMARY KEY, " +
                "PrimerNombre text, " +
                "SegundoNombre text, " +
                "PrimerApellido text, " +
                "SegundoApellido text, " +
                "Edad int, " +
                "Grado text)");

        EducaDB.execSQL("CREATE TABLE Notas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "alumno_id INTEGER, " + // Clave foránea para enlazar con Alumnos
                "nota INTEGER, " +
                "FOREIGN KEY(alumno_id) REFERENCES Alumnos(documentoId))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase EducaDB, int oldVersion, int newVersion) {
        EducaDB.execSQL(" DROP TABLE Alumnos");
        EducaDB.execSQL(" DROP TABLE Notas");
        onCreate(EducaDB);
    }
}
