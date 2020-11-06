package com.example.bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pgrInicio;
    private EditText edTxtUsuario, edTxtPass;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgrInicio = (ProgressBar)findViewById(R.id.pgrInicio);
        pgrInicio.setVisibility(View.INVISIBLE);

        edTxtUsuario = (EditText)findViewById(R.id.edTxtUsuario);
        edTxtPass = (EditText)findViewById(R.id.edTxtPass);

        btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask <String, Void, String> {

        @Override
        protected void onPreExecute() {
            pgrInicio.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            String nombre = edTxtUsuario.getText().toString();
            String pass = edTxtPass.getText().toString();

            pgrInicio.setVisibility(View.INVISIBLE);

            if(nombre.equalsIgnoreCase("android") && pass.equalsIgnoreCase("123")) {
                Intent intent = new Intent(getBaseContext(), Home_act.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getBaseContext(), "Usuario y/o Contraseña Incorrecta(s)", Toast.LENGTH_LONG).show();
            }
        }
    }
}