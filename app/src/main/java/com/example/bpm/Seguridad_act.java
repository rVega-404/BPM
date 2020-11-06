package com.example.bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad_act extends AppCompatActivity {
    private EditText edTxt;
    private TextView txtEncriptado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_act);

        edTxt = (EditText)findViewById(R.id.edTxt);
        txtEncriptado = (TextView)findViewById(R.id.txtEncriptado);
    }

    private SecretKeySpec generateKey(String password)throws Exception {
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sh.digest(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    public String Encriptar(String datos, String password)throws Exception {
        if(!edTxt.getText().toString().isEmpty()) {
            SecretKeySpec secretKeySpec = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
            String datosEncriptadosString = android.util.Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
            return datosEncriptadosString;
        } else {
            Toast.makeText(this, "Ingrese Contraseña", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void Encriptar(View view) {
        try {
            String mensaje = Encriptar(edTxt.toString(), txtEncriptado.toString());
            txtEncriptado.setText(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}