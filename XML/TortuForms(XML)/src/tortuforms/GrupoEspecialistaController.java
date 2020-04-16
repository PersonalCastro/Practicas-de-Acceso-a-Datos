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
public class GrupoEspecialistaController {
    
    private GrupoEspecialistaModel datosPersistentesGruposEspecialistas;
    
    
    public GrupoEspecialistaController(){
        
        datosPersistentesGruposEspecialistas = new GrupoEspecialistaModel();
    }
    
    
    public void getGruposEspecialistas(ArrayList<GrupoEspecialista> auxGruposEspecialistas){
        
        datosPersistentesGruposEspecialistas.getDatosGruposEspecialistas(auxGruposEspecialistas);
    }
    /**/

     public int generateId(){
        return (this.datosPersistentesGruposEspecialistas.lastId() + 1);
    }   

    public void persistenciaDeDatos(ArrayList<GrupoEspecialista> nuevosDatos){
        this.datosPersistentesGruposEspecialistas.persistenciaDeDatos(nuevosDatos);
    }
    

    public static void printArrayGruposEspecialistas(ArrayList<GrupoEspecialista> gruposEspecialistasPrint){
        for(GrupoEspecialista grupoEspecialistaPrint: gruposEspecialistasPrint){
            System.out.println("Id: " + grupoEspecialistaPrint.getId());
            System.out.println("Id referencia: " + grupoEspecialistaPrint.getIdReferencia_refugio());
            System.out.println("Especialidad: " + grupoEspecialistaPrint.getEspecialidad());
            System.out.println("Capacidad: " + grupoEspecialistaPrint.getCapacidad());
            System.out.println("Informes: " + grupoEspecialistaPrint.getInformes());
            if(grupoEspecialistaPrint.isExpedicion()){
                System.out.println("Si estan de expedición");
            }else{
                System.out.println("No estan de expedición");
            }
        }
        System.out.println("-----------------");

    }
}
