package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
                    crearXML(Nombre, Direccion, Poblacion, CIF, Telefono, Email) ;
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
        File archivoXML = new File(getFilesDir(),"cliente_" + CIF + ".xml");

        // ① Obtenga la instancia de fábrica del analizador DocumentBuilder DocumentBuilderFactory y obtenga el objeto DocumentBuilder
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // ② Obtener un objeto de documento no vacío asociado con el archivo de disco
        Document doc = newDocumentBuilder.parse(archivoXML);
        // ③ Obtener el nodo raíz del objeto de documento a través del objeto de documento
        Element root = doc.getDocumentElement();
    }
}