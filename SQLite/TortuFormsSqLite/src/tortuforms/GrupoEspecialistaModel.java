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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author PersonalCastro
 */
class GrupoEspecialistaModel {
   
    private static String url;
    private static Connection conn;
    
    
    private ResultSet rs;
    private Statement stmt;
    
    ArrayList <GrupoEspecialista> gruposEspecialistas;

    public GrupoEspecialistaModel(){
        gruposEspecialistas = new ArrayList();
        
        url = "jdbc:sqlite:E:/Java/Code/TortuForms/Tortugas.db";
        conn = null;
        
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
            this.conn = DriverManager.getConnection(this.url);
            stmt = this.conn.createStatement();
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        
        try{
            
            this.rs = this.stmt.executeQuery("SELECT * FROM grupoespecialista Where id > 0");
            
            while (rs.next()) {
                gruposEspecialistasDb.add(new GrupoEspecialista(this.rs.getInt("id"), rs.getString("especialidad"), rs.getInt("capacidad"), rs.getBoolean("expedicion"), rs.getInt("informes"), rs.getInt("idReferencia_refugio") ));

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
        
        return gruposEspecialistasDb;
    }
   
    
    private void addToDB(GrupoEspecialista newGrupoEspecialista){
    
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Insert into grupoespecialista(id, especialidad, capacidad, expedicion, informes, idReferencia_refugio)" + " values(?,?,?,?,?,?)");
            
            try {

                stm.setString(1, String.valueOf(newGrupoEspecialista.getId()));
                stm.setString(2, String.valueOf(newGrupoEspecialista.getEspecialidad()));
                stm.setString(3, String.valueOf(newGrupoEspecialista.getCapacidad()));
                stm.setString(4, String.valueOf(newGrupoEspecialista.isExpedicion()));
                stm.setString(5, String.valueOf(newGrupoEspecialista.getInformes()));
                stm.setString(6, String.valueOf(newGrupoEspecialista.getIdReferencia_refugio()));

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
    
    private void modifyOnDB(GrupoEspecialista modifiedGrupoEspecialista){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Update grupoespecialista Set id = '" + String.valueOf(modifiedGrupoEspecialista.getId()) + "', especialidad = '" + modifiedGrupoEspecialista.getEspecialidad()+
                    "', capacidad = '" + String.valueOf(modifiedGrupoEspecialista.getCapacidad()) + "', expedicion = '" + String.valueOf(modifiedGrupoEspecialista.isExpedicion()) + 
                    "', informes = '" + String.valueOf(modifiedGrupoEspecialista.getInformes()) + "', idReferencia_refugio = '" + String.valueOf(modifiedGrupoEspecialista.getIdReferencia_refugio()) + 
                    "' Where id = " + String.valueOf(modifiedGrupoEspecialista.getId()));
                       
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
    
    private void eliminateFromDB(GrupoEspecialista eliminatedGrupoEspecialista){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Delete From grupoespecialista Where id = '" + eliminatedGrupoEspecialista.getId() + "'");
            
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
