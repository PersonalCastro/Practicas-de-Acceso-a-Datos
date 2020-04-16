package ORM;
// Generated 05-mar-2020 17:57:41 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cuidador generated by hbm2java
 */
public class Cuidador  implements java.io.Serializable {


     private int id;
     private Grupoespecialista grupoespecialista;
     private String nombre;
     private Integer edad;
     private Boolean jefe;
     private String dni;
     private Set tortugas = new HashSet(0);

    public Cuidador() {
    }

	
    public Cuidador(int id) {
        this.id = id;
    }
    public Cuidador(int id, Grupoespecialista grupoespecialista, String nombre, Integer edad, Boolean jefe, String dni, Set tortugas) {
       this.id = id;
       this.grupoespecialista = grupoespecialista;
       this.nombre = nombre;
       this.edad = edad;
       this.jefe = jefe;
       this.dni = dni;
       this.tortugas = tortugas;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Grupoespecialista getGrupoespecialista() {
        return this.grupoespecialista;
    }
    
    public void setGrupoespecialista(Grupoespecialista grupoespecialista) {
        this.grupoespecialista = grupoespecialista;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return this.edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public Boolean getJefe() {
        return this.jefe;
    }
    
    public void setJefe(Boolean jefe) {
        this.jefe = jefe;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public Set getTortugas() {
        return this.tortugas;
    }
    
    public void setTortugas(Set tortugas) {
        this.tortugas = tortugas;
    }




}


