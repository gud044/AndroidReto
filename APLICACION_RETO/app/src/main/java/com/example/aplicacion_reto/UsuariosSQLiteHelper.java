package com.example.aplicacion_reto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
   /*Dar de alta: Se recogerán los datos del partner: nombre, dirección, población, CIF, teléfono, email y lo asociaremos al
    comercial correspondiente.
            ● En la confirmación del nuevo partner se enviará al archivo de nuevos partners del día que posteriormente se enviarán a su
    delegación (formato XML)
    */


    String sqlCreate1 = "CREATE TABLE Comerciales (idComercial INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, usuario TEXT, nombre TEXT, apellido TEXT, contraseña TEXT)";
    String sqlCreate2 = "CREATE TABLE Citas (fecha DATETIME NOT NULL, descripcion TEXT)";
    String sqlCreate3 = "CREATE TABLE Partners (idPartner AUTOINTEGER PRIMARY KEY NOT NULL, nombre TEXT, direccion TEXT, poblacion TEXT, cif TEXT, telefono TEXT, email TEXT, idComercial INTEGER, CONSTRAINT ck_idComerial FOREIGN KEY (idComercial) REFERENCES Comerciales (idComercial))";
    String sqlCreate4 = "CREATE TABLE Cab_Pedidos (idPedido INTEGER, idComercial INTEGER, idPartner INTEGER, fechaPedido DATETIME, CONSTRAINT ck_idPartner FOREIGN KEY (idPartner) REFERENCES Partners (idPartner), CONSTRAINT ck_idComerial FOREIGN KEY (idComercial) REFERENCES Comerciales (idComercial))";
    String sqlCreate5 = "CREATE TABLE Lin_Pedidos (idPedido INTEGER, linea INTEGER, idAlmacen INTEGER, cantidad INTEGER, precio INTEGER)";
    //faltan las constraints de foreign key de idPedido, idAlmacen)

    public UsuariosSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);
        db.execSQL(sqlCreate4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);
        db.execSQL(sqlCreate4);
    }
}