package com.example.marciov.orcamento;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ExtratoContas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato_contas);
        setTitle(R.string.extrato_contas);
        init();
    }

    private void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(R.string.conta);
        tv0.setTextSize(20);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(R.string.credito);
        tv1.setTextSize(20);
        tv1.setPadding(0,0,30,0);
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText(R.string.debito);
        tv2.setTextSize(20);
        tbrow0.addView(tv2);

        stk.addView(tbrow0);

        ArrayList<Conta> contas = new ContaDAO(this).buscar();

        Double saldo = 0D;
        Double ctasPagar = 0D;
        Double ctasReceber = 0D;

        for(Conta cta: contas) {
            Double valor = cta.getValor();
            if (valor > 0) {
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(cta.getDescricao());
                t1v.setGravity(Gravity.LEFT);
                tbrow.addView(t1v);

                boolean isCredito = cta.getTipo().equals("C");

                if (cta.getQuitado().equals("N")) {
                    if (isCredito) {
                        ctasReceber += valor;
                    } else {
                        ctasPagar += valor;
                    }
                }

                if (isCredito) {
                    saldo += valor;
                } else {
                    saldo -= valor;
                }
                TextView t2v = new TextView(this);
                t2v.setText((isCredito ? NumberFormat.getInstance().format(valor) : "   "));
                t2v.setGravity(Gravity.RIGHT);
                tv1.setPadding(0,0,30,0);
                tbrow.addView(t2v);

                TextView t3v = new TextView(this);
                t3v.setText((isCredito ? "   " :  NumberFormat.getInstance().format(valor)));
                t3v.setGravity(Gravity.RIGHT);
                tbrow.addView(t3v);

                stk.addView(tbrow);
            }
        }

        TableRow tbrow1 = new TableRow(this);
        TextView tv4 = new TextView(this);
        tv4.setText(R.string.balanco);
        tv4.setTextSize(20);
        tv4.setPadding(0,0,30,0);
        tbrow1.addView(tv4);

        TextView tv5 = new TextView(this);
        tv5.setText(NumberFormat.getInstance().format(saldo));
        tv5.setTextSize(20);
        tv5.setGravity(Gravity.RIGHT);
        tbrow1.addView(tv5);

        stk.addView(tbrow1);

        TableRow tbrow2 = new TableRow(this);

        TextView tv6 = new TextView(this);
        tv6.setText(R.string.contaapagar);
        tv6.setPadding(0,0,30,0);
        tbrow2.addView(tv6);

        TextView tv7 = new TextView(this);
        tv7.setText(NumberFormat.getInstance().format(ctasPagar));
        tv7.setGravity(Gravity.RIGHT);
        tv7.setTextColor(Color.RED);
        tbrow2.addView(tv7);

        stk.addView(tbrow2);

        TableRow tbrow3 = new TableRow(this);

        TextView tv8 = new TextView(this);
        tv8.setText(R.string.contasareceber);
        tv8.setPadding(0,0,30,0);
        tbrow3.addView(tv8);

        TextView tv9 = new TextView(this);
        tv9.setText(NumberFormat.getInstance().format(ctasReceber));
        tv9.setGravity(Gravity.RIGHT);
        tbrow3.addView(tv9);

        stk.addView(tbrow3);

        Double saldoAtual = ctasReceber - ctasPagar;

        for(Conta cta: contas) {
            Double saldoContas = cta.getSaldo();
            if (!saldoContas.equals(0D) && cta.getTipo().equals("C")) {
                TableRow tbrowsdo = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(cta.getDescricao());
                t1v.setGravity(Gravity.LEFT);
                tbrowsdo.addView(t1v);

                TextView t2v = new TextView(this);
                t2v.setText(saldoContas.toString());
                t2v.setGravity(Gravity.RIGHT);
                tbrowsdo.addView(t2v);

                saldoAtual += saldoContas;

                stk.addView(tbrowsdo);
            }
        }

        TableRow tbrow4 = new TableRow(this);

        TextView tv10 = new TextView(this);
        tv10.setText(R.string.saldoatual);
        tv10.setPadding(0,0,30,0);
        tv10.setTextSize(20);
        tbrow4.addView(tv10);

        TextView tv11 = new TextView(this);
        tv11.setText(NumberFormat.getInstance().format(saldoAtual));
        if (saldoAtual > 0) {
            tv11.setTextColor(Color.BLUE);
        } else {
            tv11.setTextColor(Color.RED);
        }
        tv11.setGravity(Gravity.RIGHT);
        tv11.setTextSize(20);
        tbrow4.addView(tv11);

        stk.addView(tbrow4);

        TableRow tbrow5 = new TableRow(this);

        TextView tv12 = new TextView(this);
        tv12.setText(R.string.financiamentos);
        tv12.setPadding(0,0,30,0);
        tv12.setTextSize(20);

        tbrow5.addView(tv12);

        stk.addView(tbrow5);

        for(Conta cta: contas) {
            Double saldoContas = cta.getSaldo();
            if (!saldoContas.equals(0D) && cta.getTipo().equals("D")) {
                TableRow tbrowsdo = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(cta.getDescricao());
                t1v.setGravity(Gravity.LEFT);
                tbrowsdo.addView(t1v);

                TextView t2v = new TextView(this);
                t2v.setText(NumberFormat.getInstance().format(saldoContas));
                t2v.setGravity(Gravity.RIGHT);
                tbrowsdo.addView(t2v);

                stk.addView(tbrowsdo);
            }
        }
    }
}
