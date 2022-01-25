package com.example.aplicacion_reto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int REQUES_CODE2=2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPruebaBDD = findViewById(R.id.btnPruebaBDD);
        Button menu1 = findViewById(R.id.btnMenu1);
        Button menu2 = findViewById(R.id.btnMenu2);
        Button menu3 = findViewById(R.id.btnMenu3);
        Button menu4 = findViewById(R.id.btnMenu4);
        Intent calendarioIntent =new Intent(this, CalendarioActivity.class);
        Intent partnersIntent =new Intent(this, PartnersActivity.class);
        //Intent pedidosIntent =new Intent(this, falta el layout .class);
        Intent enviosIntent =new Intent(this, activity_envios.class);

        Button btnMaps = findViewById(R.id.btnMaps);



        TextView telefono=findViewById(R.id.lbl_telefono);
        TextView gmail=findViewById(R.id.lbl_gmail);

        verificarPermisos();


        //Listener Telefono movil
        telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numero_telefono=telefono.getText().toString();
                String url="tel:+34"+numero_telefono;

                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        //Listener Gmail
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_gmail=gmail.getText().toString();
                Toast toast = Toast.makeText(getApplicationContext(), ""+txt_gmail, Toast.LENGTH_LONG);
                toast.show();
                String url="mailto:"+txt_gmail;
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(calendarioIntent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(partnersIntent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(pedidosIntent);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(enviosIntent);
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=43.30472651965252,-2.01687088777822");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btnPruebaBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuariosSQLiteHelper usdbh =
                        new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

                SQLiteDatabase db = usdbh.getWritableDatabase();

                //Si hemos abierto correctamente la base de datos
                if(db != null)
                {
                    //Insertamos 5 usuarios de ejemplo

                        db.execSQL("INSERT INTO Almacen_Deleg (descripcion) " +
                                "VALUES ('Ejemplo')");
                    //Cerramos la base de datos
                    db.close();
                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int PermisoLectura=ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int PermisoEscritura=ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int PermisoManage=ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE);

//        if (PermisoLectura == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this,"Permiso de lectura obtenido",Toast.LENGTH_SHORT);
//        }else{
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUES_CODE1);
//        }

        if (PermisoEscritura == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permiso de escritura obtenido",Toast.LENGTH_SHORT);
        }else{
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUES_CODE2);
        }
//
//        if (PermisoManage == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this,"Permiso manage obtenido",Toast.LENGTH_SHORT);
//        }else{
//            requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},REQUES_CODE3);
//        }

    }
}