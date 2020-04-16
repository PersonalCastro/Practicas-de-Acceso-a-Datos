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
public class Refugio implements Serializable {
    
    private int id;
    private String nombre;
    private String ciudad;
    private boolean abierto;
    private int sucursales;
    

    public Refugio(int id, String nombre, String ciudad, boolean abierto, int sucursales){
      
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.abierto = abierto;
        this.sucursales = sucursales;
    }
    
    public Refugio(){
        
        this.id = 0;
        this.nombre = "";
        this.ciudad = "";
        this.abierto = false;
        this.sucursales = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }
    
    public void setSucursales(int sucursales) {
        this.sucursales = sucursales;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public boolean isAbierto() {
        return abierto;
    }
    
    public int getSucursales() {
        return sucursales;
    }
    
}
