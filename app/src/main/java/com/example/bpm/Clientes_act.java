package com.example.bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class Clientes_act extends AppCompatActivity {
    private EditText edTxtCodigo, edTxtNombre, edTxtSalario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);

        edTxtCodigo = (EditText)findViewById(R.id.edTxtCodigo);
        edTxtNombre= (EditText)findViewById(R.id.edTxtNombre);
        edTxtSalario = (EditText)findViewById(R.id.edTxtSalario);
    }

    public void GuardarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = edTxtCodigo.getText().toString();
        String nombre = edTxtNombre.getText().toString();
        String salario = edTxtSalario.getText().toString();

        if(!codigo.isEmpty() || !nombre.isEmpty() || !salario.isEmpty()) {
            ContentValues contentValues = new ContentValues();

            contentValues.put("codigo", codigo);
            contentValues.put("nombre", nombre);
            contentValues.put("salario", salario);

            database.insert("cliente", null, contentValues);
            database.close();

            Toast.makeText(this, "Cliente guardado", Toast.LENGTH_LONG).show();

            edTxtCodigo.setText("");
            edTxtNombre.setText("");
            edTxtSalario.setText("");
        } else {
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void MostarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = edTxtCodigo.getText().toString();

        if(!codigo.isEmpty()) {
            Cursor fila = database.rawQuery("SELECT nombre, salario FROM cliente WHERE codigo=" + codigo, null);

            if(fila.moveToFirst()) {
                edTxtNombre.setText(fila.getString(0));
                edTxtSalario.setText(fila.getString(1));
            } else {
                Toast.makeText(this, "No hay campos en la tabla", Toast.LENGTH_LONG).show();
            }
        } else  {
            Toast.makeText(this, "No existe cliente con código asociado", Toast.LENGTH_LONG).show();
        }
    }

    public void EliminarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = edTxtCodigo.getText().toString();

        if(!codigo.isEmpty()) {
            database.delete("cliente", "codigo=" + codigo, null);
            database.close();

            Toast.makeText(this, "Has eliminado un insumo", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ingrese un código", Toast.LENGTH_LONG).show();
        }

        edTxtCodigo.setText("");
        edTxtNombre.setText("");
        edTxtSalario.setText("");
    }

    public void ActualizarCliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = edTxtCodigo.getText().toString();
        String nombre  = edTxtNombre.getText().toString();
        String salario = edTxtSalario.getText().toString();

        ContentValues contentValues = new ContentValues();

        contentValues.put("codigo", codigo);
        contentValues.put("nombre", nombre);
        contentValues.put("salario", salario);

        if(!codigo.isEmpty()){
            database.update("cliente", contentValues, "codigo=" + codigo, null);
            database.close();
            Toast.makeText(this, "Has actualizado el insumo", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ingrese un código", Toast.LENGTH_LONG).show();
        }

        edTxtCodigo.setText("");
        edTxtNombre.setText("");
        edTxtSalario.setText("");
    }
}