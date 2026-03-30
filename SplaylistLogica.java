package com.tp1p3.demo;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SplaylistLogica {

    @Autowired
    private SplaylistRepo repository;
    private Scanner leer = new Scanner(System.in);

   public void agregarTema() {
    Splaylist t = new Splaylist();
    System.out.print("Titulo de la cancion: "); t.setTitulo(leer.nextLine());
    System.out.print("Nombre del Interprete: "); t.setInterprete(leer.nextLine());
    System.out.print("Cantidad de temas en el album: "); t.setCantidadTemas(leer.nextInt());
    System.out.print("Duración total: "); t.setDuracionTotal(leer.nextDouble());
    leer.nextLine(); 

    repository.save(t); 
    System.out.println("Tema guardado");
}

    public void mostrarTemas() {
        repository.findAll().forEach(set -> {
            System.out.println("id: " + set.getId() + ", Titulo: " + set.getTitulo() +  ", Interprete: " + set.getInterprete() + ", Temas: " + set.getCantidadTemas() + ", Duracion: " + set.getDuracionTotal());});
    }

    public void modificarTema() {
        System.out.print("ID del tema a modificar: ");
        int id = leer.nextInt(); leer.nextLine();
        
        if (repository.existsById(id)) {
            Splaylist t = new Splaylist();
            t.setId(id);
            System.out.print("Nuevo titulo de la cancion: "); t.setTitulo(leer.nextLine());
            System.out.print("Nuevo nombre del interprete: "); t.setInterprete(leer.nextLine());
            System.out.print("Nueva cantidad de temas en el álbum: "); t.setCantidadTemas(leer.nextInt());
            System.out.print("Nueva duración total:  "); t.setDuracionTotal(leer.nextDouble());
            
            repository.save(t); 
            System.out.println("Tema modificado");
        } else {
            System.out.println("No se encontró ningún tema con el id: " + id);
        }
    }

    public void borrarTema() {
        System.out.print("Id del tema a borrar: ");
        int id = leer.nextInt();
        if (repository.existsById(id)) {
            repository.deleteById(id);
            System.out.println("Tema borrado");
        } else {
            System.out.println("Error al intentar eliminar un tema: ID no existe.");
        }
    }

    public void buscarTema() {
        System.out.print("ID del tema a buscar: ");
        int id = leer.nextInt();
        Optional<Splaylist> res = repository.findById(id);
        
        System.out.println("Datos de busqueda: ");
        if (res.isPresent()) {
            Splaylist t = res.get();
            System.out.println("ID: " + t.getId() + " Titulo: " + t.getTitulo() + " Interprete: " + t.getInterprete() + " Temas: " + t.getCantidadTemas() + " Duración: " + t.getDuracionTotal());
        } else {
            System.out.println("No se encontró ningun tema con el id: " + id);
        }
    }
}