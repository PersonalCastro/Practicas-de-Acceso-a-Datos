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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author PersonalCastro
 */
class CuidadorModel {
    
    private static String url;

    ArrayList <Cuidador> cuidadores;
    
    public CuidadorModel(){
        cuidadores = new ArrayList();

        url = "E:/Java/Code/TortuForms/Tortugas.pb";

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
    
    public void persistenciaDeDatos(ArrayList<Cuidador> nuevosDatos, int posicion, int tipo_de_uso){

        this.cuidadores.clear();
        
        for(Cuidador cuidador: nuevosDatos){
            cuidadores.add(cuidador);
        }
        
        this.addDataToDb(nuevosDatos, posicion, tipo_de_uso);
        
    }
    
    
    /* Aqui ira el Acceso a los datos de formas distintas */
    
    private void addDataToDb(ArrayList<Cuidador> nuevosDatos, int posicion, int tipo_de_uso){
        
        if(nuevosDatos.size() == 0){
            this.cuidadores.clear();

        }else{
            
            if(tipo_de_uso == 1){
                
                addToDB(nuevosDatos.get(posicion));
                
            }else if (tipo_de_uso == 2){
                
                modifyOnDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 3){
                
                eliminateFromDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 4){
                
                for(Cuidador aux: nuevosDatos){
                    if(aux.getIdReferencia_grupoEspecialista()== posicion){
                        aux.setIdReferencia_grupoEspecialista(0);
                        modifyOnDB(aux);
                    }
                }
                
            }
        }
    }
    
    private void getDataFromDb(){
        
        this.cuidadores = getInfoCuidadoresFicheroSqLite();
    }
    
    
/*      :_-----_:      */

    private ArrayList getInfoCuidadoresFicheroSqLite(){
        
        ArrayList<Cuidador> cuidadoresDb = new ArrayList();
        
        try{
            
            ObjectContainer db = Db4o.openFile(url);

            Query q = db.query();
            q.constrain(Cuidador.class);

            ObjectSet result = q.execute();


            while(result.hasNext()) {

                cuidadoresDb.add((Cuidador)result.next());

            }

            db.close();
        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }

        
        return cuidadoresDb;
    }
    
    
    private void addToDB(Cuidador newCuidador){
            
        ObjectContainer db = Db4o.openFile(url);
        db.store(newCuidador);
        db.close();
        
    }
    
    private void modifyOnDB(Cuidador modifiedCuidador){
      
        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(Cuidador.class);
        
        q.descend("id").constrain(new Integer(modifiedCuidador.getId())).equal();
        ObjectSet result = q.execute();
        
        Cuidador c = (Cuidador) result.next();    
        c.setNombre(modifiedCuidador.getNombre());
        c.setEdad(modifiedCuidador.getEdad());
        c.setJefe(modifiedCuidador.isJefe());
        c.setDni(modifiedCuidador.getDni());
        c.setIdReferencia_grupoEspecialista(modifiedCuidador.getIdReferencia_grupoEspecialista());
        
        db.store(c);
        
        db.close();
        
    }
    
    private void eliminateFromDB(Cuidador eliminatedCuidador){
        
        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(Cuidador.class);
        
        q.descend("id").constrain(new Integer(eliminatedCuidador.getId())).equal();
        ObjectSet result = q.execute();
        
        Cuidador c = (Cuidador) result.next();    
        db.delete(c);
        
        db.close();
        
    }
    
    
}
