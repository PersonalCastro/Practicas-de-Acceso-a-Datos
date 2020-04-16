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
public class CuidadorController {
    
    private CuidadorModel datosPersistentesCuidador;
    
    
    public CuidadorController(){
        
        datosPersistentesCuidador = new CuidadorModel();
    }
    
    
    public void getCuidadores(ArrayList<Cuidador> auxCuidador){
        
        datosPersistentesCuidador.getDatosCuidador(auxCuidador);
    }
    /**/
    
     public int generateId(){
        return (this.datosPersistentesCuidador.lastId() + 1);
    }   
    
    public void persistenciaDeDatos(ArrayList<Cuidador> nuevosDatos){
        this.datosPersistentesCuidador.persistenciaDeDatos(nuevosDatos);
    }
    
    

    public static void printArrayCuidadores(ArrayList<Cuidador> cuidadoresPrint){
        for(Cuidador cuidadorPrint: cuidadoresPrint){
            System.out.println("Id: " + cuidadorPrint.getId());
            System.out.println("Id referencia: " + cuidadorPrint.getIdReferencia_grupoEspecialista());
            System.out.println("Dni: " + cuidadorPrint.getDni());
            System.out.println("Nombre: " + cuidadorPrint.getNombre());
            System.out.println("Edad: " + cuidadorPrint.getEdad());
            if(cuidadorPrint.isJefe()){
                System.out.println("Si es Jefe");
            }else{
                System.out.println("No es Jefe");
            }
        }
        System.out.println("-----------------");
    }
}
