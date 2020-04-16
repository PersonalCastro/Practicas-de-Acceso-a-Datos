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
class CuidadorModel {
    
    ArrayList <Cuidador> cuidadores;
    
    public CuidadorModel(){
        cuidadores = new ArrayList();

        //Este metodo de debugeo
        //this.datosCuidadoresEjemplo();
        this.getDataFromDb();
    }
    
    public void datosCuidadoresEjemplo(){
        cuidadores.add(new Cuidador(1,"Pablo",19,false, "1234678H", 1));
        cuidadores.add(new Cuidador(2,"aquamike23",23,true, "87654321T", 2));
    }
    
    public void getDatosCuidador(ArrayList<Cuidador> auxCuidador ){
                
        /* 
            Este metodo seria el intercambieble
        */
        for(Cuidador cuidador: cuidadores){
            auxCuidador.add(cuidador);
        }
        /*
            Este metodo seria el intercambieble 
        */
                        
    }
    
    public int lastId(){
        int lastId = 0;
        for(Cuidador cuidador: cuidadores){
            
            if(cuidador.getId() > lastId){
                lastId = cuidador.getId();
            }
        }
        return lastId;
    } 
    
    public void persistenciaDeDatos(ArrayList<Cuidador> nuevosDatos){

        this.cuidadores.clear();
        
        for(Cuidador cuidador: nuevosDatos){
            cuidadores.add(cuidador);
        }
        
        this.addDataToDb(nuevosDatos);
        
    }
    
    
    /* Aqui ira el Acceso a los datos de formas distintas */
    
    private void addDataToDb(ArrayList<Cuidador> nuevosDatos){
        
        File ruta = new File("cuidadoresInf.pab");
        if(nuevosDatos.size() != 0){
            this.generarNuevoFicheroAleatorioDeCuidadores(nuevosDatos, ruta);
        }else{
            this.cuidadores.clear();
            ruta.delete();
        }
    }
    
    private void getDataFromDb(){
        
        this.cuidadores = getInfoCuidadoresFicheroDeAccesoSerializable();
    }
    
    
    private static void generarNuevoFicheroAleatorioDeCuidadores(ArrayList<Cuidador> cuidadores, File ruta){
        
        try{
            
            boolean exists = ruta.exists();
            if(exists){
                ruta.delete();
            }

            FileOutputStream fileout = new FileOutputStream(ruta,true);
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  
                        
            for(Cuidador cuidador: cuidadores){
                dataOS.writeObject(cuidador);
            }
            
            dataOS.close();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    private static ArrayList getInfoCuidadoresFicheroDeAccesoSerializable(){
        ArrayList<Cuidador> auxCuidadores = new ArrayList();        
        
        Cuidador cuidador;
        try{
            File fichero = new File("cuidadoresInf.pab");
            
            ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
            
            try {
                while (true) {
                        cuidador = (Cuidador) dataIS.readObject();
                        auxCuidadores.add(cuidador);
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
        return auxCuidadores;
    }
}
