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
public class Cuidador implements Serializable {
    
    private int id;
    private String nombre;
    private int edad;
    private boolean jefe;
    private String dni;
    private int idReferencia_grupoEspecialista;
    

    public Cuidador(int id, String nombre, int edad, boolean jefe, String dni, int idReferencia_grupoEspecialista){
      
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.jefe = jefe;
        this.dni = dni;
        this.idReferencia_grupoEspecialista = idReferencia_grupoEspecialista;
    }
    
    public Cuidador(){
        
        this.id = 0;
        this.nombre = "";
        this.edad = 0;
        this.jefe = false;
        this.dni = "";
        this.idReferencia_grupoEspecialista = 0;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIdReferencia_grupoEspecialista(int idReferencia_grupoEspecialista) {
        this.idReferencia_grupoEspecialista = idReferencia_grupoEspecialista;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setJefe(boolean jefe) {
        this.jefe = jefe;
    }

    public int getId() {
        return id;
    }
    
    public int getIdReferencia_grupoEspecialista() {
        return idReferencia_grupoEspecialista;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isJefe() {
        return jefe;
    }
    
    public String getDni() {
        return dni;
    }
    
    
}
