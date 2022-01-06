package com.example.aplicacion_reto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
    int REQUES_CODE1=1;
    int REQUES_CODE2=2;
    int REQUES_CODE3=3;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menu1 = findViewById(R.id.btnMenu1);
        Intent calendarioIntent =new Intent(this, CalendarioActivity.class);



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