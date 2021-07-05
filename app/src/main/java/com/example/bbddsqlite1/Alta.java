package com.example.bbddsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Alta extends AppCompatActivity {

    SQLiteDatabase db = null;
    private EditText mNombre , mCif, mImporte;
    private Button btnGrabar, btnSalir;
    private TextView resultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
        mNombre= (EditText) findViewById(R.id.et_nombre_alta);
        mCif = (EditText) findViewById(R.id.et_cif_alta);
        mImporte = (EditText) findViewById(R.id.et_importe_alta);
        btnGrabar= (Button)findViewById(R.id.btn_grabar_alta);
        btnSalir = (Button) findViewById(R.id.btn_salir_alta);
        resultado = (TextView)findViewById(R.id.tv_resultado_alta);


    }

    public void cerrarVentana(View view) {
        finish();
    }



    public void Grabar(View view) {

        BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBContabilidad", null, 1);
        //Abrimos la base de datos 'DBContabilidad' en modo escritura
        db = usdbh.getWritableDatabase();

        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre",mNombre.getText().toString());
        nuevoRegistro.put("cif", mCif.getText().toString());
        nuevoRegistro.put( "importe" , Integer.parseInt(mImporte.getText().toString()));
        //Insertamos el registro en la base de datos
        // primer parametro; tabla de la base de datos, Facturas
        //segundo parametro, siempre nulo menos en los autoincrementales

        db.insert("Facturas", null, nuevoRegistro);
        //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
        //hace necesario en casos muy puntuales
        //(por ejemplo para poder insertar registros completamente vacíos)


        this.resultado.setText("ALTA CORRECTA");
        Toast.makeText(this, "Resgistro dado de alta correctamente", Toast.LENGTH_LONG).show();

//Cerramos la base de datos
//db.close();


    }
}

