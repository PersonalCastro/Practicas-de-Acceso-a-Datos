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
class CuidadorModel {
    
    private static String url;
    private static Connection conn;
    
    
    private ResultSet rs;
    private Statement stmt;
    
    ArrayList <Cuidador> cuidadores;
    
    public CuidadorModel(){
        cuidadores = new ArrayList();

        url = "jdbc:sqlite:E:/Java/Code/TortuForms/Tortugas.db";
        conn = null;

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
            this.conn = DriverManager.getConnection(this.url);
            stmt = this.conn.createStatement();
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        
        try{
            
            this.rs = this.stmt.executeQuery("SELECT * FROM cuidador Where id > 0");
            
            while (rs.next()) {
                cuidadoresDb.add(new Cuidador(this.rs.getInt("id"), rs.getString("nombre"), rs.getInt("edad"), rs.getBoolean("jefe"), rs.getString("dni"), rs.getInt("idReferencia_grupoEspecialista") ));

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
        
        return cuidadoresDb;
    }
    
    
    private void addToDB(Cuidador newCuidador){
    
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Insert into cuidador(id, nombre, edad, jefe, dni, idReferencia_grupoEspecialista)" + " values(?,?,?,?,?,?)");
            
            try {

                stm.setString(1, String.valueOf(newCuidador.getId()));
                stm.setString(2, newCuidador.getNombre());
                stm.setString(3, String.valueOf(newCuidador.getEdad()));
                stm.setString(4, String.valueOf(newCuidador.isJefe()));
                stm.setString(5, String.valueOf(newCuidador.getDni()));
                stm.setString(6, String.valueOf(newCuidador.getIdReferencia_grupoEspecialista()));

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
    
    private void modifyOnDB(Cuidador modifiedCuidador){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Update cuidador Set id = '" + String.valueOf(modifiedCuidador.getId()) + "', nombre = '" + modifiedCuidador.getNombre()+
                    "', edad = '" + String.valueOf(modifiedCuidador.getEdad()) + "', jefe = '" + String.valueOf(modifiedCuidador.isJefe()) + 
                    "', dni = '" + String.valueOf(modifiedCuidador.getDni()) + "', idReferencia_grupoEspecialista = '" + String.valueOf(modifiedCuidador.getIdReferencia_grupoEspecialista()) + 
                    "' Where id = " + String.valueOf(modifiedCuidador.getId()));
                       
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
    
    private void eliminateFromDB(Cuidador eliminatedCuidador){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Delete From cuidador Where id = '" + eliminatedCuidador.getId() + "'");
            
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
