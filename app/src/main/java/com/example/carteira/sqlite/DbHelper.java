package com.example.carteira.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CarteiraDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LANCAMENTOS_NAME = "lancamentos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORIA = "categoria";
    public static final String COLUMN_DESCRICAO = "decricao";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_DIA = "dia";
    public static final String COLUMN_MES = "mes";
    public static final String COLUMN_ANO = "ano";

    public static final String TABLE_CATEGORIA_NAME = "categorias";
    public static final String COLUMN_NOME = "nome";

    private static final String CREATE_TABLE_LANCAMENTOS =
            "CREATE TABLE " + TABLE_LANCAMENTOS_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CATEGORIA + " TEXT, " +
                    COLUMN_DESCRICAO + " TEXT, " +
                    COLUMN_TIPO + " TEXT, " +
                    COLUMN_VALOR + " REAL, " +
                    COLUMN_DIA + " INTEGER, " +
                    COLUMN_MES + " INTEGER, " +
                    COLUMN_ANO + " INTEGER)";

    private static final String CREATE_TABLE_CATEGORIA =
            "CREATE TABLE " + TABLE_CATEGORIA_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LANCAMENTOS);
        db.execSQL(CREATE_TABLE_CATEGORIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LANCAMENTOS_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA_NAME);
        onCreate(db);
    }
}
