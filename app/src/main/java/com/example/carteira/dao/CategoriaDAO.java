package com.example.carteira.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carteira.model.Categoria;
import com.example.carteira.sqlite.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final SQLiteDatabase db;

    public CategoriaDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void inserirCategoria(Categoria categoria) {
        // INSERT INTO categorias (categoria) VALUES ("lanche")
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NOME, categoria.getNome());
        db.insert(DbHelper.TABLE_CATEGORIA_NAME, null, values);
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        Cursor cursor = db.query(DbHelper.TABLE_CATEGORIA_NAME,
                new String[] {DbHelper.COLUMN_ID, DbHelper.COLUMN_NOME},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                categorias.add(new Categoria(id, nome));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return categorias;
    }

}
