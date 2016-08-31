package com.example.marciov.orcamento;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MarcioV on 11/08/2016.
 */
public class ContaAdapter extends ArrayAdapter<Conta> {

    private Context context;
    private ArrayList<Conta> contas;

    public ContaAdapter(Context context, ArrayList<Conta> contas) {
        super(context, 0, contas);
        this.context = context;
        this.contas = contas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Conta itemPosicao = this.contas.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.conta_item, null);
        final View layout = convertView;

        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(itemPosicao.getDescricao());

        TextView textViewValor = (TextView) convertView.findViewById(R.id.textView2);
        textViewValor.setText(itemPosicao.getValor().toString());

        Button button = (Button) convertView.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContaNovaActivity.class);
                intent.putExtra("descricao", itemPosicao.getDescricao());
                intent.putExtra("tipo", itemPosicao.getTipo());
                intent.putExtra("valor",itemPosicao.getValor());
                intent.putExtra("saldo",itemPosicao.getSaldo());
                intent.putExtra("quitado",itemPosicao.getQuitado());
                intent.putExtra("_id",itemPosicao.getId());
                context.startActivity(intent);
            }
        });

        Button button2 = (Button) convertView.findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ContaDAO(context).delete(itemPosicao);
                layout.setVisibility(View.GONE);
            }
        });

        return convertView;
    }
}
