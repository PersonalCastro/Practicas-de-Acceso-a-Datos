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
public class TortugaController {
    
    private TortugaModel datosPersistentesTortugas;
    
    
    public TortugaController(){
        
        datosPersistentesTortugas = new TortugaModel();
    }
    
    
    public void getTortugas(ArrayList<Tortuga> auxTortugas){
        
        datosPersistentesTortugas.getDatosTortugas(auxTortugas);
    }
    /**/

    public int generateId(){
        return (this.datosPersistentesTortugas.lastId() + 1);
    }

    public void persistenciaDeDatos(ArrayList<Tortuga> nuevosDatos){
        this.datosPersistentesTortugas.persistenciaDeDatos(nuevosDatos);
    }
    

    public static void printArrayTortugas(ArrayList<Tortuga> tortugasPrint){

        for(Tortuga tortugaPrint: tortugasPrint){
            System.out.println("Id: " + tortugaPrint.getId());
            System.out.println("Id referencia: " + tortugaPrint.getIdReferencia_Cuidador());
            System.out.println("Apodo: " + tortugaPrint.getApodo());
            System.out.println("Peso: " + tortugaPrint.getPeso());
            System.out.println("Edad: " + tortugaPrint.getEdad());
            if(tortugaPrint.isHiberna()){
                System.out.println("Si hiberna");
            }else{
                System.out.println("No hiberna");
            }
        }
        System.out.println("-----------------");
    }
}
