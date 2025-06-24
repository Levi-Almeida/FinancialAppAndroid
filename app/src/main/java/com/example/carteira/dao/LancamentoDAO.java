package com.example.carteira.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carteira.model.Lancamento;
import com.example.carteira.sqlite.DbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LancamentoDAO {

    private final SQLiteDatabase db;

    public LancamentoDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void inserirLancamento(Lancamento lancamento) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_CATEGORIA, lancamento.getCategoria().toUpperCase(Locale.ROOT));
        values.put(DbHelper.COLUMN_DESCRICAO, lancamento.getDescricao().toUpperCase(Locale.ROOT));
        values.put(DbHelper.COLUMN_TIPO, lancamento.getTipo().toUpperCase(Locale.ROOT));
        values.put(DbHelper.COLUMN_VALOR, lancamento.getValor());
        values.put(DbHelper.COLUMN_DIA, lancamento.getDia());
        values.put(DbHelper.COLUMN_MES, lancamento.getMes());
        values.put(DbHelper.COLUMN_ANO, lancamento.getAno());

        db.insert(DbHelper.TABLE_LANCAMENTOS_NAME, null, values);
    }

    public List<Lancamento> listarLancamentos() {
        List<Lancamento> lancamentos = new ArrayList<>();
        Cursor cursor = db.query(DbHelper.TABLE_LANCAMENTOS_NAME,
                new String[]{
                        DbHelper.COLUMN_ID, DbHelper.COLUMN_CATEGORIA, DbHelper.COLUMN_DESCRICAO,
                        DbHelper.COLUMN_TIPO, DbHelper.COLUMN_VALOR, DbHelper.COLUMN_DIA,
                        DbHelper.COLUMN_MES, DbHelper.COLUMN_ANO},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String categoria = cursor.getString(1);
                String descricao = cursor.getString(2);
                String tipo = cursor.getString(3);
                double valor = cursor.getDouble(4);
                int dia = cursor.getInt(5);
                int mes = cursor.getInt(6);
                int ano = cursor.getInt(7);

                lancamentos.add(new Lancamento(id, categoria, descricao, tipo, valor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return lancamentos;
    }
}