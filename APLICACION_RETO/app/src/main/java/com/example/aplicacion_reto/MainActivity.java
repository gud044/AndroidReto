package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        TextView telefono=findViewById(R.id.lbl_telefono);
        TextView gmail=findViewById(R.id.lbl_gmail);


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
        //hola asiddbaosnfd
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

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mapView, mapFragment)
                .commit();

    }
}