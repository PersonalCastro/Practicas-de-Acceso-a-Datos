/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;


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
class TortugaModel {
    
    private static String url;
    private static Connection conn;
    
    
    private ResultSet rs;
    private Statement stmt;
    
    ArrayList <Tortuga> tortugas;
    
    public TortugaModel(){
        tortugas = new ArrayList();
        url = "jdbc:sqlite:E:/Java/Code/TortuForms/Tortugas.db";
        conn = null;
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
        
        this.tortugas = getInfoTortugasFicheroSqLite();
    }
    
    /*      :_-----_:      */
 
    private ArrayList getInfoTortugasFicheroSqLite(){
        
        ArrayList<Tortuga> tortugasDb = new ArrayList();
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            stmt = this.conn.createStatement();
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        
        try{
            
            this.rs = this.stmt.executeQuery("SELECT * FROM tortuga Where id > 0");
            
            while (rs.next()) {
                tortugasDb.add(new Tortuga(this.rs.getInt("id"), rs.getString("apodo"), rs.getDouble("peso"), rs.getBoolean("hiberna"), rs.getInt("edad"), rs.getInt("idReferencia_Cuidador") ));

            }
            
        } catch (SQLException e ) {
            System.out.println("Exception: " + e);
        } finally {
            
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }

            if (stmt != null) { 
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Exception: " + e);
                    
                }
            }
        }
        
        return tortugasDb;
    }
 
    private void addToDB(Tortuga newTortuga){
    
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Insert into tortuga(id, apodo, peso, hiberna, edad, idReferencia_Cuidador)" + " values(?,?,?,?,?,?)");
            
            try {

                stm.setString(1, String.valueOf(newTortuga.getId()));
                stm.setString(2, newTortuga.getApodo());
                stm.setString(3, String.valueOf(newTortuga.getPeso()));
                stm.setString(4, String.valueOf(newTortuga.isHiberna()));
                stm.setString(5, String.valueOf(newTortuga.getEdad()));
                stm.setString(6, String.valueOf(newTortuga.getIdReferencia_Cuidador()));

                stm.execute();

            } catch (SQLException ex){
                    System.out.println("Exception: " + ex);
            }finally{

                try{
                    conn.close();
                }catch(Exception e){
                        System.out.println("Exception: " + e);
                }

                if (stmt != null) { 

                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }catch(Exception e){
        System.out.println("Exception: " + e);
        }
    }
    
    private void modifyOnDB(Tortuga modifiedTortuga){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Update tortuga Set id = '" + String.valueOf(modifiedTortuga.getId()) + "', apodo = '" + modifiedTortuga.getApodo() +
                    "', peso = '" + String.valueOf(modifiedTortuga.getPeso()) + "', hiberna = '" + String.valueOf(modifiedTortuga.isHiberna()) + 
                    "', edad = '" + String.valueOf(modifiedTortuga.getEdad()) + "', idReferencia_Cuidador = '" + String.valueOf(modifiedTortuga.getIdReferencia_Cuidador()) + 
                    "' Where id = " + String.valueOf(modifiedTortuga.getId()));
                       
            try {

                stm.execute();

            } catch (SQLException ex){
                System.out.println("Exception: " + ex);
            }finally{
                
                try{
                    conn.close();
                }catch(Exception e){
                    System.out.println("Exception: " + e);
                }
                
                if (stmt != null) { 

                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
        
    }
    
    private void eliminateFromDB(Tortuga eliminatedTortuga){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Delete From tortuga Where id = '" + eliminatedTortuga.getId() + "'");
            
            try {
                stm.execute();
            } catch (SQLException ex){
                System.out.println("Exception: " + ex);
            }finally{
                
                try{
                    conn.close();
                }catch(Exception e){
                    System.out.println("Exception: " + e);
                }
                
                if (stmt != null) { 

                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
        
    }
    
}
