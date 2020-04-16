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
class TortugaModel {
    
    ArrayList <Tortuga> tortugas;
    
    public TortugaModel(){
        tortugas = new ArrayList();

        //Este metodo de debugeo
        //this.datosTortugasEjemplo();
        this.getDataFromDb();
    }
    
    public void datosTortugasEjemplo(){
        tortugas.add(new Tortuga(1,"Juanma",1.3,false, 5, 1));
        tortugas.add(new Tortuga(2,"Timpano",2.4,true, 1, 2));
    }
    
    public void getDatosTortugas(ArrayList<Tortuga> auxTortugas ){
                
        /* 
            Este metodo seria el intercambieble
        */
        for(Tortuga tortuga: tortugas){
            auxTortugas.add(tortuga);
        }

        /*
            Este metodo seria el intercambieble 
        */
                        
    }
    
    public int lastId(){
        int lastId = 0;
        for(Tortuga tortuga: tortugas){
            
            if(tortuga.getId() > lastId){
                lastId = tortuga.getId();
            }
        }
        return lastId;
    }
    
    public void persistenciaDeDatos(ArrayList<Tortuga> nuevosDatos){

        this.tortugas.clear();
        
        for(Tortuga tortuga: nuevosDatos){
            tortugas.add(tortuga);
        }
        
        this.addDataToDb(nuevosDatos);

    }
    
    /* Aqui ira el Acceso a los datos de formas distintas */
        
private void addDataToDb(ArrayList<Tortuga> nuevosDatos){
        
        File ruta = new File("tortugasInf.pab");
        if(nuevosDatos.size() != 0){
            this.generarNuevoFicheroAleatorioDeTortugas(nuevosDatos, ruta);
        }else{
            this.tortugas.clear();
            ruta.delete();
        }
    }
    
    private void getDataFromDb(){
        
        this.tortugas = getInfoTortugasFicheroDeAccesoSerializable();
    }
    
    
    private static void generarNuevoFicheroAleatorioDeTortugas(ArrayList<Tortuga> tortugas, File ruta){
        
        try{
            
            boolean exists = ruta.exists();
            if(exists){
                ruta.delete();
            }

            FileOutputStream fileout = new FileOutputStream(ruta,true);
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  
                        
            for(Tortuga tortuga: tortugas){
                dataOS.writeObject(tortuga);
            }
            
            dataOS.close();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    private static ArrayList getInfoTortugasFicheroDeAccesoSerializable(){
        ArrayList<Tortuga> auxTortugas = new ArrayList();        
        
        Tortuga tortuga;
        try{
            File fichero = new File("tortugasInf.pab");
            
            ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
            
            try {
                while (true) {
                        tortuga = (Tortuga) dataIS.readObject();
                        auxTortugas.add(tortuga);
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
        return auxTortugas;
    }
}
