package com.example.marciov.orcamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarcioV on 11/08/2016.
 */
public class ContaDAO {

    private SQLiteDatabase db;
    private static final String TABELA = "conta";

    public ContaDAO(Context context) {
        this.db = new SQLiteCore(context).getWritableDatabase();
    }

    public void insert(Conta conta) {
        ContentValues valores = new ContentValues();

        valores.put("descricao", conta.getDescricao());
        valores.put("tipo", conta.getTipo());
        valores.put("valor", conta.getValor());
        valores.put("saldo", conta.getSaldo());
        valores.put("quitado", conta.getQuitado());

        this.db.insert(TABELA, null, valores);
    }

    public void update(Conta conta) {
        ContentValues valores = new ContentValues();

        valores.put("descricao", conta.getDescricao());
        valores.put("tipo", conta.getTipo());
        valores.put("valor", conta.getValor());
        valores.put("saldo", conta.getSaldo());
        valores.put("quitado", conta.getQuitado());

        this.db.update(TABELA, valores, "_id = ?", new String[]{""+conta.getId()});
    }

    public void delete(Conta conta) {
        this.db.delete(TABELA, "_id = ?", new String[]{""+conta.getId()});
    }

    public ArrayList<Conta> buscar() {
        ArrayList<Conta> contas  = new ArrayList<Conta>();
        String[] colunas = new String[]{"_id", "descricao", "tipo", "valor", "saldo", "quitado"};
        Cursor cursor = this.db.query(TABELA, colunas, null, null, null, null, "descricao desc");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Conta conta = new Conta();
                conta.setId(cursor.getInt(0));
                conta.setDescricao(cursor.getString(1));
                conta.setTipo(cursor.getString(2));
                conta.setValor(cursor.getDouble(3));
                conta.setSaldo(cursor.getDouble(4));
                conta.setQuitado(cursor.getString(5));

                contas.add(conta);
            } while(cursor.moveToNext());
        }

        return contas;
    }
}
