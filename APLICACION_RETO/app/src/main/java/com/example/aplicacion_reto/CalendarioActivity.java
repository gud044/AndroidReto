package com.example.aplicacion_reto;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Calendar;

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
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        Button boton_agregar=findViewById(R.id.btnAgregar);
        CalendarView calendario=findViewById(R.id.calendarView);
        EditText nombre_texto=findViewById(R.id.txt_nombre);
        EditText nota_texto=findViewById(R.id.txt_nota);
        Button boton_prueba=findViewById(R.id.boton_prueba);

        ActivityResultLauncher<Intent> activityResultLauncher;
        //ArrayList<Cita> datos = new ArrayList<>();
        File directorio = new File("/data/data/com.example.pruebaxml/files");
        File ficheroXML = new File(directorio,"personas.xml");

        //Coge el año,mes y día del CalendarView
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView arg0, int arg1, int arg2, int arg3) {
                //Se suma 1 al mes porque enero empieza por 0
                arg2=arg2+1;
                fecha=ConvertirAFecha(arg1,arg2,arg3);
            }
        });

        boton_prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pruebaGaizka();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });

        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre,nota;
                nombre=nombre_texto.getText().toString();
                nota=nota_texto.getText().toString();


                    if(!nombre_texto.getText().toString().isEmpty() || !nota_texto.getText().toString().isEmpty()){
                        try {
                            EscribirCitas(nombre,fecha,nota);
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (TransformerException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast toast1= Toast.makeText(getApplicationContext(),"Tiene que rellenar los campos", Toast.LENGTH_LONG);
                        toast1.show();}
            }
        });
    }

    //Método para convertir de argumentos int a fecha en String
    public String ConvertirAFecha(int ano,int mes,int dia){
        String fecha=dia+"/"+mes+"/"+ano;
        return fecha;
    }

    public void EscribirCitas(String nombre, String fecha, String descripcion) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File file =new File(getFilesDir(),"cita.xml");
        file.createNewFile();
        Toast toast1 = Toast.makeText(getApplicationContext(), ""+file.getAbsolutePath(), Toast.LENGTH_SHORT);
        toast1.show();
        Toast toast = Toast.makeText(getApplicationContext(), ""+file.exists(), Toast.LENGTH_SHORT);
        toast.show();


    }



    public void PruebaSerializer(String nombre, String fecha, String descripcion) throws IOException {
        File archivoXML = new File(getFilesDir(),"cita.xml");
        if (!archivoXML.exists()){
            archivoXML.createNewFile();
        }

        Toast toast1 = Toast.makeText(getApplicationContext(), ""+archivoXML.getAbsolutePath(), Toast.LENGTH_SHORT);
        toast1.show();
        Toast toast = Toast.makeText(getApplicationContext(), ""+archivoXML.exists(), Toast.LENGTH_SHORT);
        toast.show();


        XmlSerializer xmlSerializer = Xml.newSerializer();
        OutputStreamWriter osWriter = new OutputStreamWriter(openFileOutput(archivoXML.getName().toString(), Context.MODE_APPEND));
        xmlSerializer.setOutput(osWriter);

        xmlSerializer.startTag("","root");
        xmlSerializer.startTag("","citas");
        xmlSerializer.startTag("","cita");
        xmlSerializer.startTag("","numero");
        xmlSerializer.text("1");
        xmlSerializer.endTag("","numero");
        xmlSerializer.startTag("","nombre");
        xmlSerializer.text("Este es el nombre");
        xmlSerializer.endTag("","nombre");
        xmlSerializer.endTag("","cita");
        xmlSerializer.endTag("","citas");
        xmlSerializer.endTag("", "root");
        xmlSerializer.endDocument();
        osWriter.close();

//        xmlSerializer.startTag("","descripcion");
//        xmlSerializer.text(descripcion);
//        xmlSerializer.endTag("","descripcion");
    }

    public void pruebaGaizka() throws IOException, SAXException, ParserConfigurationException {
        File archivoXML = new File(getFilesDir(),"cita.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse( archivoXML);

        document.getDocumentElement().normalize();

        NodeList listaCitas = document.getElementsByTagName("cita");
        for (int temp = 0; temp < listaCitas.getLength(); temp++) {
            Node nodo = listaCitas.item(temp);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;

                Toast toast = Toast.makeText(getApplicationContext(), ""+element.getElementsByTagName("numero").item(0).getTextContent()+", "+element.getElementsByTagName("nombre").item(0).getTextContent(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}