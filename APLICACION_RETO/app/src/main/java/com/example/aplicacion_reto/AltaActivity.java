package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AltaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        EditText edtNombre = findViewById(R.id.edtNombre);
        EditText edtDireccion = findViewById(R.id.edtDireccion);
        EditText edtPoblacion = findViewById(R.id.edtPoblacion);
        EditText edtCIF = findViewById(R.id.edtCIF);
        EditText edtTelefono = findViewById(R.id.edtTelefono);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtComercial = findViewById(R.id.edtComercial);
        Button DarAltaBtn = findViewById(R.id.btnAlta2);

        DarAltaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String vnombre = edtNombre.getText().toString();
                    String vdireccion = edtDireccion.getText().toString();
                    String vpoblacion = edtPoblacion.getText().toString();
                    String vcif = edtCIF.getText().toString();
                    String vtelefono = edtTelefono.getText().toString();
                    String vemail = edtEmail.getText().toString();
                    String vcomercial = edtComercial.getText().toString();
                    if(edtNombre.length()!=0 && edtDireccion.length()!=0 && edtPoblacion.length()!=0 && edtCIF.length()!=0 && edtTelefono.length()!=0 && edtEmail.length()!=0 && edtComercial.length()!=0) {
                        UsuariosSQLiteHelper usdbh =
                                new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

                        SQLiteDatabase db = usdbh.getWritableDatabase();

                        //Si hemos abierto correctamente la base de datos
                        if (db != null) {
                            //Insertamos 5 usuarios de ejemplo

                            db.execSQL("INSERT INTO Partners (nombre, direccion, poblacion, cif, telefono, email, idComercial) " +
                                    "VALUES ('" + vnombre +"', '"+ vdireccion +"', '"+ vpoblacion +"', '"+ vcif +"', '"+ vtelefono +"', '"+ vemail +"', "+ vcomercial +")");
                            //Cerramos la base de datos
                            db.close();
                        }
                    }
            }
        });


    }
}