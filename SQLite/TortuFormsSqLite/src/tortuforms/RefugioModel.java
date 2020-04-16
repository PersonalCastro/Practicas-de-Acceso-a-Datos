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
class RefugioModel {
    
    private static String url;
    private static Connection conn;
    
    
    private ResultSet rs;
    private Statement stmt;
    
    ArrayList <Refugio> refugios;
    
    public RefugioModel(){
        refugios = new ArrayList();
        url = "jdbc:sqlite:E:/Java/Code/TortuForms/Tortugas.db";
        conn = null;
        //Este metodo de debugeo
        //this.datosRefugiosEjemplo();
        this.getDataFromDb();
    }
  
    public void datosRefugiosEjemplo(){
        refugios.add(new Refugio(1,"Pablos's shelters","España",false, 0));
        refugios.add(new Refugio(2,"Aquamike shelters","Canada",true, 3));
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
            this.conn = DriverManager.getConnection(this.url);
            stmt = this.conn.createStatement();
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        
        try{
            
            this.rs = this.stmt.executeQuery("SELECT * FROM refugio Where id > 0");
            
            while (rs.next()) {
                refugiosDb.add(new Refugio(this.rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"), rs.getBoolean("abierto"), rs.getInt("sucursales")));

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
        
        return refugiosDb;
    }
    
    private void addToDB(Refugio newRefugio){
    
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Insert into refugio(id, nombre, ciudad, abierto, sucursales)" + " values(?,?,?,?,?)");
            
            try {

                stm.setString(1, String.valueOf(newRefugio.getId()));
                stm.setString(2, String.valueOf(newRefugio.getNombre()));
                stm.setString(3, String.valueOf(newRefugio.getCiudad()));
                stm.setString(4, String.valueOf(newRefugio.isAbierto()));
                stm.setString(5, String.valueOf(newRefugio.getSucursales()));

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
    
    private void modifyOnDB(Refugio modifiedRefugio){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Update refugio Set id = '" + String.valueOf(modifiedRefugio.getId()) + "', nombre = '" + modifiedRefugio.getNombre()+
                    "', ciudad = '" + String.valueOf(modifiedRefugio.getCiudad()) + "', abierto = '" + String.valueOf(modifiedRefugio.isAbierto()) + 
                    "', sucursales = '" + String.valueOf(modifiedRefugio.getSucursales()) + "' Where id = " + String.valueOf(modifiedRefugio.getId()));
                       
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
    
    private void eliminateFromDB(Refugio eliminatedRefugio){
        
        try{
            this.conn = DriverManager.getConnection(this.url);
            PreparedStatement stm = this.conn.prepareStatement("Delete From refugio Where id = '" + eliminatedRefugio.getId() + "'");
            
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