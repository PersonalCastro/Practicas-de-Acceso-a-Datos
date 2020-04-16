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
public class GrupoEspecialista implements Serializable {
    
    private int id;
    private String especialidad;
    private int capacidad;
    private boolean expedicion;
    private int informes;
    private int idReferencia_refugio;
    

    public GrupoEspecialista(int id, String especialidad, int capacidad, boolean expedicion, int informes, int idReferencia_refugio){
      
        this.id = id;
        this.especialidad = especialidad;
        this.capacidad = capacidad;
        this.expedicion = expedicion;
        this.informes = informes;
        this.idReferencia_refugio = idReferencia_refugio;
    }
    
    public GrupoEspecialista(){
        
        this.id = 0;
        this.especialidad = "";
        this.capacidad = 0;
        this.expedicion = false;
        this.informes = 0;
        this.idReferencia_refugio = 0;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIdReferencia_refugio(int idReferencia_refugio) {
        this.idReferencia_refugio = idReferencia_refugio;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setExpedicion(boolean expedicion) {
        this.expedicion = expedicion;
    }
    
    public void setInformes(int informes) {
        this.informes = informes;
    }

    public int getId() {
        return id;
    }
    
    public int getIdReferencia_refugio() {
        return idReferencia_refugio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isExpedicion() {
        return expedicion;
    }
    
    public int getInformes() {
        return informes;
    }
    
    
    
}
