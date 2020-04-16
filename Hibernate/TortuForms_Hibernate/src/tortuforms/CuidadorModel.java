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
import tortuforms_hibernate.Cuidador;
import tortuforms_hibernate.Grupoespecialista;


/**
 *
 * @author PersonalCastro
 */
class CuidadorModel {
    
    ArrayList <Cuidador> cuidadores;
    public static Grupoespecialista none_grupoespecialista;
    
    public CuidadorModel(){
        cuidadores = new ArrayList();
        none_grupoespecialista = new Grupoespecialista();
        
        //Este metodo de debugeo
        //this.datosCuidadoresEjemplo();
        this.getDataFromDb();
    }
    
    public void datosCuidadoresEjemplo(){
        //cuidadores.add(new Cuidador(1,"Pablo",19,false, "1234678H", 1));
        //cuidadores.add(new Cuidador(2,"aquamike23",23,true, "87654321T", 2));
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
                    if(aux.getGrupoespecialista().getId()== posicion){
                        aux.setGrupoespecialista(none_grupoespecialista);
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

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Cuidador WHERE id > 0" ); 

            List<Cuidador> listaDep  = s.createQuery( "FROM Cuidador WHERE id > 0" ).list();
            for (Cuidador axuCuidador: listaDep) { 

                cuidadoresDb.add(axuCuidador);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        return cuidadoresDb;
    }
    
    
    private void addToDB(Cuidador newCuidador){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //Save the employee in database
            session.save(newCuidador);

            //Commit the transaction
            session.getTransaction().commit();
            session.close();

        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e);
        }  
        
    }
    
    private void modifyOnDB(Cuidador modifiedCuidador){
        
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(modifiedCuidador);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
        
    }
    
    private void eliminateFromDB(Cuidador eliminatedCuidador){
        
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (eliminatedCuidador != null) {
                s.delete(eliminatedCuidador);
                s.refresh(eliminatedCuidador.getGrupoespecialista());
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
