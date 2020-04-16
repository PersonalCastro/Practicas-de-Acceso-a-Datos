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
class GrupoEspecialistaModel {
   
    private static String url;
    
    ArrayList <GrupoEspecialista> gruposEspecialistas;

    public GrupoEspecialistaModel(){
        gruposEspecialistas = new ArrayList();
        
        url = "E:/Java/Code/TortuForms/Tortugas.pb";
        
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
    
    public void persistenciaDeDatos(ArrayList<GrupoEspecialista> nuevosDatos, int posicion, int tipo_de_uso){
        
        this.gruposEspecialistas.clear();
        
        for(GrupoEspecialista grupoEspecialista: nuevosDatos){
            gruposEspecialistas.add(grupoEspecialista);
        }
        
        this.addDataToDb(nuevosDatos, posicion, tipo_de_uso);
        
    }  
    
    /* Aqui ira el Acceso a los datos de formas distintas */
  
    private void addDataToDb(ArrayList<GrupoEspecialista> nuevosDatos, int posicion, int tipo_de_uso){        
        
        if(nuevosDatos.size() == 0){
            this.gruposEspecialistas.clear();

        }else{
            
            if(tipo_de_uso == 1){
                
                addToDB(nuevosDatos.get(posicion));
                
            }else if (tipo_de_uso == 2){
                
                modifyOnDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 3){
                
                eliminateFromDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 4){
                
                for(GrupoEspecialista aux: nuevosDatos){
                    if(aux.getIdReferencia_refugio() == posicion){
                        aux.setIdReferencia_refugio(0);
                        modifyOnDB(aux);
                    }
                }
                
            }
        }
    }
    
    private void getDataFromDb(){
        
        this.gruposEspecialistas = getInfoGruposEspecialistasFicheroSqLite();
    }
    
 /*      :_-----_:      */

    private ArrayList getInfoGruposEspecialistasFicheroSqLite(){
        
        ArrayList<GrupoEspecialista> gruposEspecialistasDb = new ArrayList();
        
        try{
            
            ObjectContainer db = Db4o.openFile(url);

            Query q = db.query();
            q.constrain(GrupoEspecialista.class);

            ObjectSet result = q.execute();


            while(result.hasNext()) {

                gruposEspecialistasDb.add((GrupoEspecialista)result.next());

            }

            db.close();
        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        return gruposEspecialistasDb;
    }
   
    
    private void addToDB(GrupoEspecialista newGrupoEspecialista){
    
        ObjectContainer db = Db4o.openFile(url);
        db.store(newGrupoEspecialista);
        db.close();
    }
    
    private void modifyOnDB(GrupoEspecialista modifiedGrupoEspecialista){
        
        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(GrupoEspecialista.class);
        
        q.descend("id").constrain(new Integer(modifiedGrupoEspecialista.getId())).equal();
        ObjectSet result = q.execute();
        
        GrupoEspecialista g = (GrupoEspecialista) result.next();    
        g.setEspecialidad(modifiedGrupoEspecialista.getEspecialidad());
        g.setCapacidad(modifiedGrupoEspecialista.getCapacidad());
        g.setExpedicion(modifiedGrupoEspecialista.isExpedicion());
        g.setInformes(modifiedGrupoEspecialista.getInformes());
        g.setIdReferencia_refugio(modifiedGrupoEspecialista.getIdReferencia_refugio());
        
        db.store(g);
        
        db.close();
        
    }
    
    private void eliminateFromDB(GrupoEspecialista eliminatedGrupoEspecialista){
        
        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(GrupoEspecialista.class);
        
        q.descend("id").constrain(new Integer(eliminatedGrupoEspecialista.getId())).equal();
        ObjectSet result = q.execute();
        
        GrupoEspecialista g = (GrupoEspecialista) result.next();    
        db.delete(g);
        
        db.close();
        
    }
    
    
}
