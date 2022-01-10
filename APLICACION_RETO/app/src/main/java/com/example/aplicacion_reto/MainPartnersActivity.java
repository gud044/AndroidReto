package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPartnersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_partners);

        Button darAlta = findViewById(R.id.btnAlta);
        darAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanzar_darAlta = new Intent(MainPartnersActivity.this, DarAltaActivity.class);
                startActivity(lanzar_darAlta);
            }
        });
    }




}