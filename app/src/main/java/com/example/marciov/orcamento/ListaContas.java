package com.example.marciov.orcamento;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaContas extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.listView = (ListView)findViewById(R.id.listView);

        ArrayList<Conta> contas = new ContaDAO(this).buscar();
        ContaAdapter contaAdapter = new ContaAdapter(this, contas);

        this.listView.setAdapter(contaAdapter);

        setTitle(R.string.lista_contas);



    }

}
