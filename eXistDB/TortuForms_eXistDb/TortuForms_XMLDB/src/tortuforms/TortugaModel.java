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
import java.util.ArrayList;


/**
 *
 * @author PersonalCastro
 */
class TortugaModel {
    
    private static String url;
    
    ArrayList <Tortuga> tortugas;
    
    public TortugaModel(){
        tortugas = new ArrayList();
        url = "E:/Java/Code/TortuForms/Tortugas.pb";
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
    
    public void persistenciaDeDatos(ArrayList<Tortuga> nuevosDatos, int posicion, int tipo_de_uso){
        //Tipo de uso -->  1-añadir  2-Modificar  3-eliminar
        this.tortugas.clear();
        
        for(Tortuga tortuga: nuevosDatos){
            tortugas.add(tortuga);
        }
        
        this.addDataToDb(nuevosDatos, posicion, tipo_de_uso);

    }
    
    /* Aqui ira el Acceso a los datos de formas distintas */
        
    private void addDataToDb(ArrayList<Tortuga> nuevosDatos, int posicion, int tipo_de_uso){
        
        if(nuevosDatos.size() == 0){
            this.tortugas.clear();

        }else{
            
            if(tipo_de_uso == 1){
                
                addToDB(nuevosDatos.get(posicion));
                
            }else if (tipo_de_uso == 2){
                
                modifyOnDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 3){
                
                eliminateFromDB(nuevosDatos.get(posicion));
            }else if (tipo_de_uso == 4){
                
                for(Tortuga aux: nuevosDatos){
                    if(aux.getIdReferencia_Cuidador()== posicion){
                        aux.setIdReferencia_Cuidador(0);
                        modifyOnDB(aux);
                    }
                }
                
            }
        }

    }
    
    private void getDataFromDb(){
        
        
        this.tortugas = getInfoTortugasFicherodb4o();
    }
    
    /*      :_-----_:      */
 
    private ArrayList getInfoTortugasFicherodb4o(){
        
        ArrayList<Tortuga> tortugasDb = new ArrayList();
        try{
            
            ObjectContainer db = Db4o.openFile(url);

            Query q = db.query();
            q.constrain(Tortuga.class);

            ObjectSet result = q.execute();


            while(result.hasNext()) {

                tortugasDb.add((Tortuga)result.next());

            }

            db.close();
        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }

                
        return tortugasDb;
    }
 
    private void addToDB(Tortuga newTortuga){
    
        ObjectContainer db = Db4o.openFile(url);
        db.store(newTortuga);
        db.close();
    }
    
    private void modifyOnDB(Tortuga modifiedTortuga){

        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(Tortuga.class);
        
        q.descend("id").constrain(new Integer(modifiedTortuga.getId())).equal();
        ObjectSet result = q.execute();
        
        Tortuga t = (Tortuga) result.next();    
        t.setApodo(modifiedTortuga.getApodo());
        t.setPeso(modifiedTortuga.getPeso());
        t.setHiberna(modifiedTortuga.isHiberna());
        t.setIdReferencia_Cuidador(modifiedTortuga.getIdReferencia_Cuidador());
        t.setEdad(modifiedTortuga.getEdad());
        
        db.store(t);
        
        db.close();
        
    }
    
    private void eliminateFromDB(Tortuga eliminatedTortuga){
        
        ObjectContainer db = Db4o.openFile(url);

        Query q = db.query();
        q.constrain(Tortuga.class);
        
        q.descend("id").constrain(new Integer(eliminatedTortuga.getId())).equal();
        ObjectSet result = q.execute();
        
        Tortuga p = (Tortuga) result.next();    
        db.delete(p);
        
        db.close();

    }
    
}
