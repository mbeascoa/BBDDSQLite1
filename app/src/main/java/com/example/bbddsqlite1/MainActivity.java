package com.example.bbddsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db=null;
    private Button btnAlta, btnConsulta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alta(View view){
    Intent i = new Intent(this, Alta.class);
    startActivity(i);
    }

    public void consultar(View view){
        Intent i = new Intent(this, Consultar.class);
        startActivity(i);
    }

    public void crearBd(View view) {

        BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBContabilidad", null, 1);
       //Abrimos la base de datos 'DBContabilidad' en modo escritura
        db= usdbh.getWritableDatabase();
        /*
      //Si hemos abierto correctamente la base de datos
        if(db != null)         {
      //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)             {
       //Generamos los datos
                String nombre = "Usuario" + i;
                String cif = "CIF"+i;
                int importe = i;
       //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Facturas (codigo, nombre, cif, ) " +
                        "VALUES (" + codigo + ", '" + nombre +"')");
            }
            System.out.println("INSERTADO!!!!");
     //Cerramos la base de datos
    //db.close();
*/


        }
}