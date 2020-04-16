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
import tortuforms_hibernate.Grupoespecialista;
import tortuforms_hibernate.Refugio;


/**
 *
 * @author PersonalCastro
 */
class GrupoEspecialistaModel {
       
    ArrayList <Grupoespecialista> gruposEspecialistas;
    public static Refugio none_refugio;

    public GrupoEspecialistaModel(){
        gruposEspecialistas = new ArrayList();        
        none_refugio = new Refugio();
        
        //Este metodo de debugeo
        //this.datosGruposEspecialistasEjemplo();
        this.getDataFromDb();
    }
    
    public void datosGruposEspecialistasEjemplo(){
        //gruposEspecialistas.add(new Grupoespecialista(1,"Tortugas Tierra",4,false, 3, 1));
        //gruposEspecialistas.add(new Grupoespecialista(2,"Tortugas Mar",5,true, 2, 2));
    }
    
    public void getDatosGruposEspecialistas(ArrayList<Grupoespecialista> auxGruposEspecialistas ){
                
        /* 
            Este metodo seria el intercambieble
        */
        for(Grupoespecialista grupoEspecialista: gruposEspecialistas){
            auxGruposEspecialistas.add(grupoEspecialista);
        }
        /*
            Este metodo seria el intercambieble 
        */
                        
    }
    
    public int lastId(){
        int lastId = 0;
        for(Grupoespecialista grupoEspecialista: gruposEspecialistas){
            
            if(grupoEspecialista.getId() > lastId){
                lastId = grupoEspecialista.getId();
            }
        }
        return lastId;
    } 
    
    public void persistenciaDeDatos(ArrayList<Grupoespecialista> nuevosDatos, int posicion, int tipo_de_uso){
        
        this.gruposEspecialistas.clear();
        
        for(Grupoespecialista grupoEspecialista: nuevosDatos){
            gruposEspecialistas.add(grupoEspecialista);
        }
        
        this.addDataToDb(nuevosDatos, posicion, tipo_de_uso);
        
    }  
    
    /* Aqui ira el Acceso a los datos de formas distintas */
  
    private void addDataToDb(ArrayList<Grupoespecialista> nuevosDatos, int posicion, int tipo_de_uso){        
        
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
                
                for(Grupoespecialista aux: nuevosDatos){
                    if(aux.getRefugio().getId()== posicion){
                        aux.setRefugio(none_refugio);
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
        
        ArrayList<Grupoespecialista> gruposEspecialistasDb = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Grupoespecialista WHERE id > 0" ); 

            List<Grupoespecialista> listaDep  = s.createQuery( "FROM Grupoespecialista WHERE id > 0" ).list();
            for (Grupoespecialista axuGrupoespecialista: listaDep) { 

                gruposEspecialistasDb.add(axuGrupoespecialista);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        return gruposEspecialistasDb;
    }
   
    
    private void addToDB(Grupoespecialista newGrupoEspecialista){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //Save the employee in database
            session.save(newGrupoEspecialista);

            //Commit the transaction
            session.getTransaction().commit();
            session.close();

        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e);
        }  
        
    }
    
    private void modifyOnDB(Grupoespecialista modifiedGrupoEspecialista){
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(modifiedGrupoEspecialista);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
        
    }
    
    private void eliminateFromDB(Grupoespecialista eliminatedGrupoEspecialista){

        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (eliminatedGrupoEspecialista != null) {
                s.delete(eliminatedGrupoEspecialista);
                s.refresh(eliminatedGrupoEspecialista.getRefugio());
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
