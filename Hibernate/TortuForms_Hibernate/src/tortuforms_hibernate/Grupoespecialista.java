package tortuforms_hibernate;
// Generated 16-ene-2020 9:43:00 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Grupoespecialista generated by hbm2java
 */
public class Grupoespecialista  implements java.io.Serializable {


     private int id;
     private Refugio refugio;
     private String especialidad;
     private Integer capacidad;
     private Boolean expedicion;
     private Integer informes;
     private Set cuidadors = new HashSet(0);

    public Grupoespecialista() {
    }

	
    public Grupoespecialista(int id) {
        this.id = id;
    }
    public Grupoespecialista(int id, String especialidad, Integer capacidad, Boolean expedicion, Integer informes, Refugio refugio) {
       this.id = id;
       this.refugio = refugio;
       this.especialidad = especialidad;
       this.capacidad = capacidad;
       this.expedicion = expedicion;
       this.informes = informes;
       //this.cuidadors = cuidadors;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Refugio getRefugio() {
        return this.refugio;
    }
    
    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }
    public String getEspecialidad() {
        return this.especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public Integer getCapacidad() {
        return this.capacidad;
    }
    
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    public Boolean getExpedicion() {
        return this.expedicion;
    }
    
    public void setExpedicion(Boolean expedicion) {
        this.expedicion = expedicion;
    }
    public Integer getInformes() {
        return this.informes;
    }
    
    public void setInformes(Integer informes) {
        this.informes = informes;
    }
    public Set getCuidadors() {
        return this.cuidadors;
    }
    
    public void setCuidadors(Set cuidadors) {
        this.cuidadors = cuidadors;
    }




}


