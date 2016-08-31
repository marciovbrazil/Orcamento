package com.example.marciov.orcamento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MarcioV on 11/08/2016.
 */
public class SQLiteCore  extends SQLiteOpenHelper {

    public static final String NOMEDB = "orcamentodb";
    public static final int VERSAO = 1;

    public SQLiteCore(Context context) {
        super(context, NOMEDB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE conta(_id integer primary key autoincrement, descricao text, tipo text, valor real, saldo real, quitado text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}