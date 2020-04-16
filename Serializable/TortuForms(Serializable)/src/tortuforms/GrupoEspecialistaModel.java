/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 *
 * @author PersonalCastro
 */
class GrupoEspecialistaModel {
   
    ArrayList <GrupoEspecialista> gruposEspecialistas;

    public GrupoEspecialistaModel(){
        gruposEspecialistas = new ArrayList();
        
        //Este metodo de debugeo
        //this.datosGruposEspecialistasEjemplo();
        this.getDataFromDb();
    }
    
    public void datosGruposEspecialistasEjemplo(){
        gruposEspecialistas.add(new GrupoEspecialista(1,"Tortugas Tierra",4,false, 3, 1));
        gruposEspecialistas.add(new GrupoEspecialista(2,"Tortugas Mar",5,true, 2, 2));
    }
    
    public void getDatosGruposEspecialistas(ArrayList<GrupoEspecialista> auxGruposEspecialistas ){
                
        /* 
            Este metodo seria el intercambieble
        */
        for(GrupoEspecialista grupoEspecialista: gruposEspecialistas){
            auxGruposEspecialistas.add(grupoEspecialista);
        }
        /*
            Este metodo seria el intercambieble 
        */
                        
    }
    
    public int lastId(){
        int lastId = 0;
        for(GrupoEspecialista grupoEspecialista: gruposEspecialistas){
            
            if(grupoEspecialista.getId() > lastId){
                lastId = grupoEspecialista.getId();
            }
        }
        return lastId;
    } 
    
    public void persistenciaDeDatos(ArrayList<GrupoEspecialista> nuevosDatos){
        
        this.gruposEspecialistas.clear();
        
        for(GrupoEspecialista grupoEspecialista: nuevosDatos){
            gruposEspecialistas.add(grupoEspecialista);
        }
        
        this.addDataToDb(nuevosDatos);
        
    }  
    
    /* Aqui ira el Acceso a los datos de formas distintas */
  
    private void addDataToDb(ArrayList<GrupoEspecialista> nuevosDatos){
        
        File ruta = new File("gruposEspecialistasInf.pab");
        if(nuevosDatos.size() != 0){
            this.generarNuevoFicheroAleatorioDeGruposEspecialistas(nuevosDatos, ruta);
        }else{
            this.gruposEspecialistas.clear();
            ruta.delete();
        }
    }
    
    private void getDataFromDb(){
        
        this.gruposEspecialistas = getInfoCuidadoresFicheroDeAccesoSerializable();
    }
    
    private static void generarNuevoFicheroAleatorioDeGruposEspecialistas(ArrayList<GrupoEspecialista> gruposEspecialistas, File ruta){
        
        try{
            
            boolean exists = ruta.exists();
            if(exists){
                ruta.delete();
            }

            FileOutputStream fileout = new FileOutputStream(ruta,true);
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  
                        
            for(GrupoEspecialista grupoEspecialista: gruposEspecialistas){
                dataOS.writeObject(grupoEspecialista);
            }
            
            dataOS.close();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    private static ArrayList getInfoCuidadoresFicheroDeAccesoSerializable(){
        ArrayList<GrupoEspecialista> auxGruposEspecialistas = new ArrayList();        
        
        GrupoEspecialista grupoEspecialista;
        try{
            File fichero = new File("gruposEspecialistasInf.pab");
            
            ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
            
            try {
                while (true) {
                        grupoEspecialista = (GrupoEspecialista) dataIS.readObject();
                        auxGruposEspecialistas.add(grupoEspecialista);
                }
            } catch (EOFException eo) {
                System.out.println("FIN DE LECTURA.");
            } catch (StreamCorruptedException x) {
                System.out.println("exception: " + x);
            }

            dataIS.close(); // cerrar stream de entrada
        }catch (Exception e){
            System.out.println("Exception: " +e);
        }
        return auxGruposEspecialistas;
    }
}
