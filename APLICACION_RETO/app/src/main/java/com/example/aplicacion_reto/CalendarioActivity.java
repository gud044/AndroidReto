package com.example.aplicacion_reto;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.TimePicker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CalendarioActivity extends AppCompatActivity {
    String fecha=  new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String hora= new SimpleDateFormat("kk:mm").format(new Date());



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        TimePicker reloj=(TimePicker)findViewById(R.id.id_reloj);
        reloj.setIs24HourView(true);
        reloj.setHour(reloj.getHour()+1);

        Button boton_agregar=findViewById(R.id.btnAgregar);
        CalendarView calendario=findViewById(R.id.calendarView);
        EditText nota_texto=findViewById(R.id.txt_nota);

        //Coge el año,mes y día del CalendarView
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView arg0, int arg1, int arg2, int arg3) {
                //Se suma 1 al mes porque enero empieza por 0
                arg2=arg2+1;
                fecha=ConvertirAFecha(arg1,arg2,arg3);
            }
        });


        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                int horas=reloj.getHour();
                int minutos=reloj.getMinute();
                hora=horas+":"+minutos;



                if(nota_texto.getText().toString().length()>0) {
                    String descripcion=nota_texto.getText().toString();
                    UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();
                    db.execSQL("INSERT INTO Citas(fecha,descripcion,hora)"+"VALUES ('"+fecha+"','"+descripcion+"','"+hora+"')");
                    db.close();
                    Toast toast =Toast.makeText(getApplicationContext(),"Cita añadida correctar: "+fecha+" - "+hora+" - "+descripcion, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast =Toast.makeText(getApplicationContext(),"Tiene que poner una descripción ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


    }

    //Método para convertir de argumentos int a fecha en String
    public String ConvertirAFecha(int ano,int mes,int dia){
        String fecha=dia+"/"+mes+"/"+ano;
        return fecha;
    }

}