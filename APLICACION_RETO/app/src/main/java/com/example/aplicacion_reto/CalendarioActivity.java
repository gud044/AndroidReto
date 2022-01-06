package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
            @Override
            public void onClick(View view) {
                String nombre,nota;
                nombre=nombre_texto.getText().toString();
                nota=nota_texto.getText().toString();

                try {
                    //if(!nombre_texto.getText().toString().isEmpty() || !nota_texto.getText().toString().isEmpty()){
                     //   EscribirCitas(nombre,fecha,nota);
                    //}else{
                      //  Toast toast1= Toast.makeText(getApplicationContext(),"Tiene que rellenar los campos", Toast.LENGTH_LONG);
                      //  toast1.show();
                    EscribirCitas("nombre","fecha","descripcion");
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });

        boton_prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String archivo_nombre="citas.xml";

                File file = new File(getFilesDir(), archivo_nombre);


                Toast toast1= Toast.makeText(getApplicationContext(),""+file.getAbsoluteFile(), Toast.LENGTH_LONG);
                toast1.show();

            }
        });
    }


    //Método para convertir de argumentos int a fecha en String
    public String ConvertirAFecha(int ano,int mes,int dia){
        String fecha=dia+"/"+mes+"/"+ano;
        return fecha;
    }

    public void EscribirCitas(String nombre, String fecha, String descripcion) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        File file = new File(getFilesDir(), "citas.xml");
        //data/data/com.example.aplicacion_reto/files/citas.xml

        // ① Obtenga la instancia de fábrica del analizador DocumentBuilder DocumentBuilderFactory y obtenga el objeto DocumentBuilder
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // ② Obtener un objeto de documento no vacío asociado con el archivo de disco
        Document doc = newDocumentBuilder.parse(file);
        // ③ Obtener el nodo raíz del objeto de documento a través del objeto de documento
        Element root = doc.getDocumentElement();

        // Crear un nuevo nodo de persona
        Element person = doc.createElement("cita");
        // Crear varios nodos secundarios de persona
        Element nombre1 = doc.createElement("nombre");
        Element fecha1 = doc.createElement("fecha");
        Element descripcion1 = doc.createElement("descripcion");
        // Establecer valores para nodos secundarios
        nombre1.setTextContent ("NOMBRE");
        fecha1.setTextContent ("FECHA");
        descripcion1.setTextContent ("NOTA");
        // Añadir nodo hijo a persona
        person.appendChild(nombre1);
        person.appendChild(fecha1);
        person.appendChild(descripcion1);
        // Establecer el valor para la identificación de la persona
        //person.setAttribute("id", "3");
        // Agregar persona al nodo raíz
        root.appendChild(person);

        // Nota: El archivo XML se carga en la memoria. La modificación también se encuentra en la memoria ==》 Por lo tanto, los datos en la memoria deben sincronizarse con el disco.
        /*
         * static TransformerFactory newInstance (): Obtenga una nueva instancia de TransformerFactory.
         * abstract Transformer newTransformer (): crea un nuevo transformador que realiza una copia desde el origen al resultado.
         * transformación vacía abstracta (Source xmlSource, Result outputTarget): transforma la fuente XML en un resultado.
         */
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //DOMSource source = new DOMSource(doc);
        Source source = new DOMSource(doc);
        //StreamResult result = new StreamResult();
        Result result = new StreamResult(String.valueOf(file));
        transformer.transform (source, result); // Transformar XML ==> Fuente en resultado

    }
}