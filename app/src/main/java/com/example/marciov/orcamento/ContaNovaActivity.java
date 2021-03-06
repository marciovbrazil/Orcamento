package com.example.marciov.orcamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ContaNovaActivity extends AppCompatActivity {

    private Conta conta = new Conta();
    private EditText editTextDescricao;
    private RadioButton radioButtonCredito;
    private RadioButton radioButtonDebito;
    private EditText editTextValor;
    private EditText editTextSaldo;
    private CheckBox checkBoxQuitado;
    private boolean contaNova = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_nova);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.nova_conta);

        this.editTextDescricao = (EditText) findViewById(R.id.editText);
        this.editTextValor = (EditText) findViewById(R.id.editText2);
        this.editTextSaldo = (EditText) findViewById(R.id.editText3);
        this.radioButtonCredito = (RadioButton) findViewById(R.id.radioButton);
        this.radioButtonDebito = (RadioButton) findViewById(R.id.radioButton2);
        this.checkBoxQuitado = (CheckBox) findViewById(R.id.checkBox);

        this.editTextDescricao.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        Intent intent = getIntent();

        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                this.conta.setId(bundle.getInt("_id"));
                this.conta.setDescricao(bundle.getString("descricao"));
                this.conta.setTipo(bundle.getString("tipo"));
                this.conta.setValor(bundle.getDouble("valor"));
                this.conta.setSaldo(bundle.getDouble("saldo"));
                this.conta.setQuitado(bundle.getString("quitado"));

                this.editTextDescricao.setText(this.conta.getDescricao());
                this.radioButtonCredito.setChecked(this.conta.getTipo().equals("C"));
                this.radioButtonDebito.setChecked(this.conta.getTipo().equals("D"));
                this.editTextValor.setText(this.conta.getValor().toString());
                this.editTextSaldo.setText(this.conta.getSaldo().toString());
                this.checkBoxQuitado.setChecked(this.conta.getQuitado().equals("S"));

                contaNova = false;
            }
        }
    }

    public void salvar(View view) {
        Double saldo = Double.valueOf(0);
        try {
            saldo = new Double(this.editTextSaldo.getText().toString());
        } catch (Exception e) {

        }
        Double valor = Double.valueOf(0);
        try {
            valor = new Double(this.editTextValor.getText().toString());
        } catch (Exception e) {

        }
        this.conta.setDescricao(this.editTextDescricao.getText().toString());
        this.conta.setTipo(this.radioButtonCredito.isChecked() ? "C" : "D");
        this.conta.setValor(valor);
        this.conta.setSaldo(saldo);
        this.conta.setQuitado(this.checkBoxQuitado.isChecked() ? "S": "N");

        if (contaNova) {
            new ContaDAO(this).insert(conta);
            Toast.makeText(this, "Usuário incluído com sucesso.", Toast.LENGTH_LONG).show();
        } else {
            new ContaDAO(this).update(conta);
            Toast.makeText(this, "Usuário alterado com sucesso.", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    public void cancelar(View view) {
        finish();
    }

}
