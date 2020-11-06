package com.example.bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Home_act extends AppCompatActivity {
    private ViewFlipper vf;
    private int[] imagenes =  {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);

        vf = (ViewFlipper)findViewById(R.id.vf);

        for(int i = 0; i < imagenes.length; i++) {
            FlipImagen(imagenes[i]);
        }
    }

    public void FlipImagen(int i) {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);
        vf.addView(view);
        vf.setAutoStart(true);
        vf.setFlipInterval(3000);

        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void Info(View view) {
        Intent intent = new Intent(this,  Info_act.class);
        startActivity(intent);
    }

    public void Seguridad(View view) {
        Intent intent = new Intent(this, Seguridad_act.class);
        startActivity(intent);
    }

    public void Prestamos(View view) {
        ArrayList<String> clientes = new ArrayList<>();
        clientes.add("Axel");
        clientes.add("Roxana");
        clientes.add("Betzabe");
        clientes.add("Matias");

        ArrayList<String> creditos = new ArrayList<>();
        creditos.add("Crédito Hipotecario");
        creditos.add("Crédito Automotriz");

        Intent intent = new Intent(this, Prestamos_act.class);
        intent.putExtra("listaClientes", clientes);
        intent.putExtra("listaCreditos", creditos);
        startActivity(intent);
    }

    public void Clientes(View view) {
        Intent intent = new Intent(this, Clientes_act.class);
        startActivity(intent);
    }
}