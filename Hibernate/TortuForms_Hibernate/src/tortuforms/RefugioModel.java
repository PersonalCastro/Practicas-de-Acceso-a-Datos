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
import org.hibernate.Session;
import org.hibernate.Transaction;
import tortuforms_hibernate.Refugio;


/**
 *
 * @author PersonalCastro
 */
public class RefugioModel {
       
    ArrayList <Refugio> refugios;

    public RefugioModel(){
        refugios = new ArrayList();

        //Este metodo de debugeo
        //this.datosRefugiosEjemplo();
        this.getDataFromDb();
    }
  
    public void datosRefugiosEjemplo(){
        //refugios.add(new Refugio(1,"Pablos's shelters","España",false, 0));
        //refugios.add(new Refugio(2,"Aquamike shelters","Canada",true, 3));
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

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        return refugiosDb;
    }
    
    private void addToDB(Refugio newRefugio){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //Save the employee in database
            session.save(newRefugio);

            //Commit the transaction
            session.getTransaction().commit();
            session.close();

        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e);
        }  
    }
    
    private void modifyOnDB(Refugio modifiedRefugio){
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(modifiedRefugio);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
        
    }
    
    private void eliminateFromDB(Refugio eliminatedRefugio){
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (eliminatedRefugio != null) {
                s.delete(eliminatedRefugio);
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