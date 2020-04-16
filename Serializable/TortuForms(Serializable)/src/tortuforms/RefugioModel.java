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
class RefugioModel {
    
    ArrayList <Refugio> refugios;
    
    public RefugioModel(){
        refugios = new ArrayList();
        
        //Este metodo de debugeo
        //this.datosRefugiosEjemplo();
        this.getDataFromDb();
    }
  
    public void datosRefugiosEjemplo(){
        refugios.add(new Refugio(1,"Pablos's shelters","España",false, 0));
        refugios.add(new Refugio(2,"Aquamike shelters","Canada",true, 3));
    }
    
    public void getDatosRefugios(ArrayList<Refugio> auxRefugios ){
        
        for(Refugio refugio: refugios){
            auxRefugios.add(refugio);
        }

                        
    }
    
    public int lastId(){
        int lastId = 0;
        for(Refugio refugio: refugios){
            
            if(refugio.getId() > lastId){
                lastId = refugio.getId();
            }
        }
        return lastId;
    }

    public void persistenciaDeDatos(ArrayList<Refugio> nuevosDatos){

        this.refugios.clear();
        
        for(Refugio refugio: nuevosDatos){
            refugios.add(refugio);
        }
        
        this.addDataToDb(nuevosDatos);
        
    }    
 
    /* Aqui ira el Acceso a los datos de formas distintas */
         
    private void addDataToDb(ArrayList<Refugio> nuevosDatos){
        
        File ruta = new File("refugiosInf.pab");
        if(nuevosDatos.size() != 0){
            this.generarNuevoFicheroAleatorioDeRefugios(nuevosDatos, ruta);
        }else{
            this.refugios.clear();
            ruta.delete();
        }
    }
    
    private void getDataFromDb(){
        
        this.refugios = getInfoRefugiosFicheroDeAccesoSerializable();
    }
    
    
    private static void generarNuevoFicheroAleatorioDeRefugios(ArrayList<Refugio> refugios, File ruta){
        
        try{
            
            boolean exists = ruta.exists();
            if(exists){
                ruta.delete();
            }

            FileOutputStream fileout = new FileOutputStream(ruta,true);
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  
                        
            for(Refugio refugio: refugios){
                dataOS.writeObject(refugio);
            }
            
            dataOS.close();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    private static ArrayList getInfoRefugiosFicheroDeAccesoSerializable(){
        ArrayList<Refugio> auxrefugios = new ArrayList();        
        
        Refugio refugio;
        try{
            File fichero = new File("refugiosInf.pab");
            
            ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
            
            try {
                while (true) {
                        refugio = (Refugio) dataIS.readObject();
                        auxrefugios.add(refugio);
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
        return auxrefugios;
    }
}
