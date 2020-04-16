/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;


import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author PersonalCastro
 */
class RefugioModel {
    
    private static String url;

    
    ArrayList <Refugio> refugios;
    
    public RefugioModel(){
        refugios = new ArrayList();
        url = "E:/Java/Code/TortuForms/Tortugas.pb";
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

    public void persistenciaDeDatos(ArrayList<Refugio> nuevosDatos, int posicion, int tipo_de_uso){

        this.refugios.clear();
        
        for(Refugio refugio: nuevosDatos){
            refugios.add(refugio);
        }
        
        this.addDataToDb(nuevosDatos, posicion, tipo_de_uso);
        
    }    
 
    /* Aqui ira el Acceso a los datos de formas distintas */
         
    private void addDataToDb(ArrayList<Refugio> nuevosDatos, int posicion, int tipo_de_uso){
        
        if(nuevosDatos.size() == 0){
            this.refugios.clear();

        }else{
            
            if(tipo_de_uso == 1){
                
                addToDB(nuevosDatos.get(posicion));
                
            }else if (tipo_de_uso == 2){
                
                modifyOnDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 3){
                
                eliminateFromDB(nuevosDatos.get(posicion));
            }
        }
    }
    
    private void getDataFromDb(){
        
        this.refugios = getInfoRefugiossFicheroSqLite();
    }
    
    /*      :_-----_:      */

    
    private ArrayList getInfoRefugiossFicheroSqLite(){
        
        ArrayList<Refugio> refugiosDb = new ArrayList();
        
        try{
            
            ObjectContainer db = Db4o.openFile(url);

            Query q = db.query();
            q.constrain(Refugio.class);

            ObjectSet result = q.execute();


            while(result.hasNext()) {

                refugiosDb.add((Refugio)result.next());

            }

            db.close();
        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        return refugiosDb;
    }
    
    private void addToDB(Refugio newRefugio){
    
        ObjectContainer db = Db4o.openFile(url);
        db.store(newRefugio);
        db.close();
    }
    
    private void modifyOnDB(Refugio modifiedRefugio){
        
       ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(Refugio.class);
        
        q.descend("id").constrain(new Integer(modifiedRefugio.getId())).equal();
        ObjectSet result = q.execute();
        
        Refugio r = (Refugio) result.next();    
        r.setNombre(modifiedRefugio.getNombre());
        r.setCiudad(modifiedRefugio.getCiudad());
        r.setAbierto(modifiedRefugio.isAbierto());
        r.setSucursales(modifiedRefugio.getSucursales());
        
        db.store(r);
        
        db.close();
        
    }
    
    private void eliminateFromDB(Refugio eliminatedRefugio){
        
        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(Refugio.class);
        
        q.descend("id").constrain(new Integer(eliminatedRefugio.getId())).equal();
        ObjectSet result = q.execute();
        
        Refugio r = (Refugio) result.next();    
        db.delete(r);
        
        db.close();
        
    }

}