package juanxxiii;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        insertarDatos();
    }


    public static void insertarDatos(){
    int numeroDeHilos = 0;
    int numeroDeRegistros = 0;
    int rango = 0;
    int resto = 0;
    Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Inserte el numero de hilos que desea utilizar:");
            numeroDeHilos = scanner.nextInt();
            System.out.println("Inserte el numero de registros que desea cargar en la BBDD:");
            numeroDeRegistros = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Dato introducido incorrecto");
            insertarDatos();
        }

        rango = numeroDeRegistros / numeroDeHilos;
        resto = numeroDeRegistros%numeroDeHilos;
        for (int x = 0; x < numeroDeHilos; x++) {
            if (resto != 0 && x < resto){
                CargarDatosBBDD cargarDatosBBDD = new CargarDatosBBDD(rango +1);
                cargarDatosBBDD.start();
                try {
                    cargarDatosBBDD.join();
                } catch (InterruptedException e) {
                    System.err.println("Error al cargar datos en la BBDD");
                }
            }else{
                CargarDatosBBDD cargarDatosBBDD = new CargarDatosBBDD(rango);
                cargarDatosBBDD.start();
                try {
                    cargarDatosBBDD.join();
                } catch (InterruptedException e) {
                    System.err.println("Error al cargar datos en la BBDD");
                }
            }
        }
    }
}
