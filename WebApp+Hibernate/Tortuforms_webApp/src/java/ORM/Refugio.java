package ORM;
// Generated 05-mar-2020 17:57:41 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Refugio generated by hbm2java
 */
public class Refugio  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String ciudad;
     private Boolean abierto;
     private Integer sucursales;
     private Set grupoespecialistas = new HashSet(0);

    public Refugio() {
    }

	
    public Refugio(int id) {
        this.id = id;
    }
    public Refugio(int id, String nombre, String ciudad, Boolean abierto, Integer sucursales, Set grupoespecialistas) {
       this.id = id;
       this.nombre = nombre;
       this.ciudad = ciudad;
       this.abierto = abierto;
       this.sucursales = sucursales;
       this.grupoespecialistas = grupoespecialistas;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public Boolean getAbierto() {
        return this.abierto;
    }
    
    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }
    public Integer getSucursales() {
        return this.sucursales;
    }
    
    public void setSucursales(Integer sucursales) {
        this.sucursales = sucursales;
    }
    public Set getGrupoespecialistas() {
        return this.grupoespecialistas;
    }
    
    public void setGrupoespecialistas(Set grupoespecialistas) {
        this.grupoespecialistas = grupoespecialistas;
    }




}


