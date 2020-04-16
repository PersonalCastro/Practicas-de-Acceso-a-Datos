/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;



import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import tortuforms_hibernate.Tortuga;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import tortuforms_hibernate.Cuidador;



/**
 *
 * @author PersonalCastro
 */
class TortugaModel {
        
    ArrayList <Tortuga> tortugas;
    public static Cuidador none_cuidador;

    
    public TortugaModel(){
        tortugas = new ArrayList();
        none_cuidador = new Cuidador();

        //Este metodo de debugeo
        //this.datosTortugasEjemplo();
        this.getDataFromDb();
                
    }
    
    public void datosTortugasEjemplo(){
        //tortugas.add(new Tortuga(1,"Juanma",1.3,false, 5, 1));
        //tortugas.add(new Tortuga(2,"Timpano",2.4,true, 1, 2));
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
                    if(aux.getCuidador().getId()== posicion){
                        aux.setCuidador(none_cuidador);
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

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Tortuga WHERE id > 0" ); 

            List<Tortuga> listaDep  = s.createQuery( "FROM Tortuga WHERE id > 0" ).list();
            for (Tortuga axuTortuga: listaDep) { 

                tortugasDb.add(axuTortuga);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
                
        return tortugasDb;
    }
 
    private void addToDB(Tortuga newTortuga){
    
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //Save the employee in database
            session.save(newTortuga);

            //Commit the transaction
            session.getTransaction().commit();
            session.close();

        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e);
        }   
    
    }
    
    private void modifyOnDB(Tortuga modifiedTortuga){
        
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(modifiedTortuga);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
    }
    
    private void eliminateFromDB(Tortuga eliminatedTortuga){
        
        
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (eliminatedTortuga != null) {
                s.delete(eliminatedTortuga);
                s.refresh(eliminatedTortuga.getCuidador());
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
    }
    
}
