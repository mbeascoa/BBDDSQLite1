package com.example.bbddsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Consultar extends AppCompatActivity {
    private EditText mNombreC, mImporteC;
    private Button btnBuscar;
    private Spinner spFacturas;
    private TextView mResultadoC;
    private ArrayList<String> arrayFactura;
    ArrayList<String> spFactura = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        mResultadoC = (TextView) findViewById(R.id.tv_resultado_consulta);
        mNombreC = (EditText) findViewById(R.id.et_nombre_consultas);
        mImporteC = (EditText) findViewById(R.id.et_importe_consultas);

        spFacturas = (Spinner) findViewById(R.id.spinnerFacturas);
        //Array de String para volcar sobre el Spinner
        ArrayList<String> opciones = recuperarFacturas();

        // Podemos decidir el tipo de lista que mostraremos en el primer
        // argumento del constructor de la clase ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        // Asociar el ArrayAdapter al Spinner
        spFacturas.setAdapter(adapter);


    }

    private ArrayList<String> recuperarFacturas() {


        try {
            String[] args = new String[]{null};
            String codigo = "";


            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBContabilidad", null, 1);

            SQLiteDatabase db = usdbh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT codigo FROM Facturas", args);
            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    codigo = c.getString(0);
                    Log.i("Codigo", "codigo: "+codigo);
                    spFactura.add(codigo);
                } while (c.moveToNext());
            }

            this.mResultadoC.setText("Código usuario:" + codigo);
            Toast.makeText(this, "Código Usuario " + codigo, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return spFactura;
    }

   public void  buscar (View view) {

           String datoCodigo=spFacturas.getSelectedItem().toString();

       try {
           String[] args = new String[] {datoCodigo};
           String codigo="",nombre="", cif="", importe="";
           BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBUsuarios", null, 1);

           SQLiteDatabase db = usdbh.getReadableDatabase();
           Cursor c = db.rawQuery("SELECT codigo,nombre FROM Usuarios where nombre=?",args);
           //Nos aseguramos de que existe al menos un registro
           if (c.moveToFirst()) {
               //Recorremos el cursor hasta que no haya más registros
               do {
                   codigo = c.getString(0);
                   nombre = c.getString(1);
                   cif = c.getString(2);
                   importe = c.getString( 3);


               } while(c.moveToNext());
           }

           this.mNombreC.setText(nombre);
           this.mImporteC.setText(importe);
           Toast.makeText(this, "Nombre : "+ nombre + "  Importe : "+importe, Toast.LENGTH_LONG).show();
       }
       catch(Exception e){
           System.out.println(e.toString());

       }


       }


    public void cerrarVentana(View view) {
        finish();
    }
}


