/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;

import java.util.ArrayList;

/**
 *
 * @author PersonalCastro
 */
public class RefugioController {
    
    private RefugioModel datosPersistentesRefugios;
    
    
    public RefugioController(){
        
        datosPersistentesRefugios = new RefugioModel();
    }
    
    
    public void getRefugios(ArrayList<Refugio> auxRefugios){
        
        datosPersistentesRefugios.getDatosRefugios(auxRefugios);
    }
    /**/
    
    public int generateId(){
        return (this.datosPersistentesRefugios.lastId() + 1);
    }
    
    public void persistenciaDeDatos(ArrayList<Refugio> nuevosDatos){
        this.datosPersistentesRefugios.persistenciaDeDatos(nuevosDatos);
    }

    
    

    public static void printArrayRefugios(ArrayList<Refugio> refugiosPrint){
        
        for(Refugio refugioPrint: refugiosPrint){
            System.out.println("Id: " + refugioPrint.getId());
            System.out.println("Nombre: " + refugioPrint.getNombre());
            System.out.println("Ciudad: " + refugioPrint.getCiudad());
            System.out.println("Sucursales: " + refugioPrint.getSucursales());
            if(refugioPrint.isAbierto()){
                System.out.println("Si esta abierto");
            }else{
                System.out.println("No esta abierto");
            }
        }
        System.out.println("-----------------");
    }
}
