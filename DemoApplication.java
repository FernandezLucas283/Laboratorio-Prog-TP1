package com.tp1p3.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private SplaylistLogica logica;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner leer = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("1. Agregar tema");
            System.out.println("2. Mostrar todos");
            System.out.println("3. Modificar tema ");
            System.out.println("4. Borrar tema ");
            System.out.println("5. Buscar tema");
            System.out.println("0. Salir");
            System.out.print("Elija una opcion: ");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1 : logica.agregarTema(); 
                break;
                case 2 : logica.mostrarTemas(); 
                break;
                case 3 : logica.modificarTema(); 
                break;
                case 4 : logica.borrarTema(); 
                break;
                case 5 : logica.buscarTema(); 
                break;
                case 0 : System.out.println("Saliendo"); 
                break;
                default : System.out.println("No valido"); 
                break;
            }
        } while (opcion != 0);
    }
}