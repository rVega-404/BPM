package com.example.bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Prestamos_act extends AppCompatActivity {
    private Spinner spnCliente, spnTipoCredito;
    private TextView txtTotal;
    private int saldoCliente, valorCredito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos_act);

        spnCliente = (Spinner)findViewById(R.id.spnCliente);
        ArrayList<String> listaClientes = (ArrayList<String>) getIntent().getSerializableExtra("listaClientes");
        ArrayAdapter<String> adapterClientes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
        spnCliente.setAdapter(adapterClientes);
        spnTipoCredito =(Spinner)findViewById(R.id.spnTipoCredito);
        ArrayList<String> listaCreditos = (ArrayList<String>) getIntent().getSerializableExtra("listaCreditos");
        ArrayAdapter<String> adapterCreditos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCreditos);
        spnTipoCredito.setAdapter(adapterCreditos);

        txtTotal = (TextView)findViewById(R.id.txtTotal);
    }

    public void CalcularPrestamo(View view) {
        String cliente = spnCliente.getSelectedItem().toString();
        String tipoCredito = spnTipoCredito.getSelectedItem().toString();

        if (cliente.equalsIgnoreCase("Axel")) {
            saldoCliente = 750000;
        } else if (cliente.equalsIgnoreCase("Roxana")) {
            saldoCliente = 900000;
        } else {
            saldoCliente = 0;
        }

        if (tipoCredito.equalsIgnoreCase("Crédito Hipotecario")) {
            valorCredito = 1000000;
        } else if (tipoCredito.equalsIgnoreCase("Crédito Automotriz")) {
            valorCredito = 500000;
        }

        txtTotal.setText("El saldo de " + cliente + " es: $" + (saldoCliente + valorCredito));
    }

    public void CalcularDeuda(View view) {
        String cliente = spnCliente.getSelectedItem().toString();
        String tipoCredito = spnTipoCredito.getSelectedItem().toString();
        int valorCuota = 0;

        if (cliente.equalsIgnoreCase("Axel")) {
            saldoCliente = 750000;
        } else if (cliente.equalsIgnoreCase("Roxana")) {
            saldoCliente = 900000;
        }

        if (tipoCredito.equalsIgnoreCase("Crédito Hipotecario")) {
            valorCredito = 1000000;
            valorCuota = (saldoCliente + valorCredito) / 12;
            txtTotal.setText("El pago se realizará en 12 cuotas de: $" + valorCuota);
        } else if (tipoCredito.equalsIgnoreCase("Crédito Automotriz")) {
            valorCredito = 500000;
            valorCuota = (saldoCliente + valorCredito) / 8;
            txtTotal.setText("El pago se realizará en 8 cuotas de: $" + valorCuota);
        }
    }
}