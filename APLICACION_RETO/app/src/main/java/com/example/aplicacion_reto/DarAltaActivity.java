package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DarAltaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_alta);

        EditText Nombre = findViewById(R.id.edtNombre);
        EditText Direccion = findViewById(R.id.edtDireccion);
        EditText Poblacion = findViewById(R.id.edtPoblacion);
        EditText CIF = findViewById(R.id.edtCIF);
        EditText Telefono = findViewById(R.id.edtTelefono);
        EditText Email = findViewById(R.id.edtEmail);
        Button DarAltaBtn = findViewById(R.id.btnAlta2);

        DarAltaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(Nombre.length()!=0 && Direccion.length()!=0 && Poblacion.length()!=0 && CIF.length()!=0 && Telefono.length()!=0 && Email.length()!=0){
                        crearXML(Nombre, Direccion, Poblacion, CIF, Telefono, Email);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void crearXML(EditText Nombre, EditText Direccion, EditText Poblacion, EditText CIF, EditText Telefono, EditText Email) throws IOException, SAXException, ParserConfigurationException {

        File archivoXML = new File(getFilesDir(),"partner_"+CIF+".xml");
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

        xmlSerializer.startTag("","Partners");
        xmlSerializer.startTag("","Partner");
        xmlSerializer.startTag("","Nombre");
        xmlSerializer.text(Nombre.getText().toString());
        xmlSerializer.endTag("","Nombre");
        xmlSerializer.startTag("","Direccion");
        xmlSerializer.text(Direccion.getText().toString());
        xmlSerializer.endTag("","Direccion");
        xmlSerializer.startTag("","Poblacion");
        xmlSerializer.text(Poblacion.getText().toString());
        xmlSerializer.endTag("","Poblacion");
        xmlSerializer.startTag("","CIF");
        xmlSerializer.text(CIF.getText().toString());
        xmlSerializer.endTag("","CIF");
        xmlSerializer.startTag("","Telefono");
        xmlSerializer.text(Telefono.getText().toString());
        xmlSerializer.endTag("","Telefono");
        xmlSerializer.startTag("","Email");
        xmlSerializer.text(Email.getText().toString());
        xmlSerializer.endTag("","Email");
        xmlSerializer.endTag("","Partner");
        xmlSerializer.endTag("", "Partners");

        xmlSerializer.endDocument();
        osWriter.close();

    }
}