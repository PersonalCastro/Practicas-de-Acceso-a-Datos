/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;

import java.io.Serializable;


/**
 *
 * @author PersonalCastro
 */
public class Tortuga implements Serializable {
    
    private int id;
    private String apodo;
    private double peso;
    private boolean hiberna;
    private int edad;
    private int idReferencia_Cuidador;
    

    public Tortuga(int id, String apodo, double peso, boolean hiberna, int edad, int idReferencia_Cuidador){
      
        this.id = id;
        this.apodo = apodo;
        this.peso = peso;
        this.hiberna = hiberna;
        this.edad = edad;
        this.idReferencia_Cuidador = idReferencia_Cuidador;
    }
    
    public Tortuga(){
        
        this.id = 0;
        this.apodo = "";
        this.peso = 0.0;
        this.hiberna = false;
        this.edad = 0;
        idReferencia_Cuidador = 0;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIdReferencia_Cuidador(int idReferencia_Cuidador) {
        this.idReferencia_Cuidador = idReferencia_Cuidador;
    }
    
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setHiberna(boolean hiberna) {
        this.hiberna = hiberna;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }
    
    public int getIdReferencia_Cuidador() {
        return idReferencia_Cuidador;
    }

    public String getApodo() {
        return apodo;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isHiberna() {
        return hiberna;
    }
    
    public int getEdad(){
        return edad;
    }
    
    
}
