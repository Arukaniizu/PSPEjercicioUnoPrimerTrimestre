package juanxxiii;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CargarDatosBBDD extends  Thread{
    int rango;
    public CargarDatosBBDD(int rango){
        this.rango = rango;
    }

    @Override
    public void run() {
        InsertarDatos(rango);
    }


    public synchronized void InsertarDatos(int rango){
    Connection conexionBaseDeDatos;
    Statement consultaBBDD = null;
    try {
        Thread.sleep(75);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    try {
        conexionBaseDeDatos = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1","DAM2020_PSP","DAM2020_PSP");
        consultaBBDD = conexionBaseDeDatos.createStatement();
        System.out.println(conexionBaseDeDatos);
    } catch (SQLException e) {
        System.err.println("Error al conectar a la base de datos");
    }
    for (int x = 0; x < rango; x++) {
        Faker faker = new Faker();
        faker.internet().emailAddress();
        try {
            consultaBBDD.executeUpdate("insert into empleados (EMAIL, INGRESOS) values ('" + faker.internet().emailAddress() +"','"+ sueldo(1) +"')");
            sueldo(1);
        } catch (SQLException e) {
            System.err.println("Error en la query");
        }
    }
}

    public static int sueldo(int random){
        int randomSueldo = 0;
        for (int i = 0; i < random; i++) {
            randomSueldo   = (int) (Math.random() * (10-1000 + 1) +1000);
        }
        return randomSueldo;
    }
}
