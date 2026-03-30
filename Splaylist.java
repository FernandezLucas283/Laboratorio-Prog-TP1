package com.tp1p3.demo;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "splaylist")
public class Splaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    private String titulo;
    private String interprete;

    @Column(name = "cantidad_temas")
    private int cantidadTemas;

    @Column(name = "duracion_total")
    private double duracionTotal;

    public Splaylist() {}

    public int getId() { 
        return id; }
    public void setId(int id) { 
        this.id = id; }
    public String getTitulo() { 
        return titulo; }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; }
    public String getInterprete() { 
        return interprete; }
    public void setInterprete(String interprete) { 
        this.interprete = interprete; }
    public int getCantidadTemas() { return cantidadTemas; }
    public void setCantidadTemas(int cantidadTemas) { 
        this.cantidadTemas = cantidadTemas; }
    public double getDuracionTotal() { 
        return duracionTotal; }
    public void setDuracionTotal(double duracionTotal) { this.duracionTotal = duracionTotal; }
}