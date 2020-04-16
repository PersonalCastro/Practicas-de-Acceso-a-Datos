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
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;


/**
 *
 * @author PersonalCastro
 */
class RefugioModel {
    
    private static String url;

    
    ArrayList <Refugio> refugios;
    
    public RefugioModel(){
        refugios = new ArrayList();
        url = "E:/Java/Code/TortuForms/Tortugas.pb";
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
        
        XQConnection c = null; 
        try { 

            c = obtenConexion(); 
            String cad = "doc('/db/TortuForms/Refugios.xml')/Refugios/refugio"; 
            XQExpression xqe = c.createExpression(); 
            XQResultSequence xqrs = xqe.executeQuery(cad);
            int i=1; 
            
            String cadenaFinal = new String();
            
            while (xqrs.next()) { 
                XMLStreamReader xsr = xqrs.getSequenceAsStream();

                while (xsr.hasNext()) { 


                    if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) { 
                        String aux = String.valueOf(xsr.getText());

                        if (!aux.equals("")) { 

                            cadenaFinal += aux + "#";
                            //System.out.println(aux);
                        } 
                    }      

                    //System.out.println("1");
                    xsr.next(); 

                } 
            } 
            
            cadenaFinal = cadenaFinal.replace("\r", "");    
            cadenaFinal = cadenaFinal.replace("\n", "");
            cadenaFinal = cadenaFinal.replace(" ", "");
            cadenaFinal = "#" + cadenaFinal + "%";
            cadenaFinal = cadenaFinal.replace("###", "%");
            cadenaFinal = cadenaFinal.replace("##", "-");

            cadenaFinal = cadenaFinal.substring(cadenaFinal.indexOf("-") + 1,cadenaFinal.length());
            while(cadenaFinal.contains("%")){
                
                Refugio aux = new Refugio();
               
                //id
                aux.setId(Integer.valueOf(cadenaFinal.substring(0, cadenaFinal.indexOf("-"))));
                //nombre
                cadenaFinal = cadenaFinal.substring(cadenaFinal.indexOf("-") + 1,cadenaFinal.length());
                aux.setNombre(cadenaFinal.substring(0, cadenaFinal.indexOf("-")));
                //ciudad
                cadenaFinal = cadenaFinal.substring(cadenaFinal.indexOf("-") + 1,cadenaFinal.length());
                aux.setCiudad(cadenaFinal.substring(0, cadenaFinal.indexOf("-")));       
                //boolean
                cadenaFinal = cadenaFinal.substring(cadenaFinal.indexOf("-") + 1,cadenaFinal.length());
                aux.setAbierto(Boolean.valueOf(cadenaFinal.substring(0, cadenaFinal.indexOf("-"))));     
                //sucursales
                cadenaFinal = cadenaFinal.substring(cadenaFinal.indexOf("-") + 1,cadenaFinal.length());

                if( cadenaFinal.substring(0 , cadenaFinal.indexOf("%")).contains("-") ){
                    aux.setSucursales(Integer.valueOf(cadenaFinal.substring(0, cadenaFinal.indexOf("-"))));
                }else{
                    aux.setSucursales(Integer.valueOf(cadenaFinal.substring(0, cadenaFinal.indexOf("%"))));
                }
                refugiosDb.add(aux); 
                cadenaFinal = cadenaFinal.substring(cadenaFinal.indexOf("%") + 1,cadenaFinal.length());
            }
        } 
        catch (XQException e) { 
            muestraErrorXQuery(e);
        } 
        catch (Exception e) { 
           // e.printStackTrace(); 
        } 
        finally { 
            try { 
                if (c != null) { 
                    c.close(); 
                } 
            } 
            catch (XQException xe) { 
                xe.printStackTrace(); 
            } 
        } 
        return refugiosDb;
    }
    
    private void addToDB(Refugio newRefugio){
    
        XQConnection c = null; 
        String addToDB = "update insert (\n<refugio>"  + "\n<id>" + newRefugio.getId() + "</id>"+ "\n<nombre>" + newRefugio.getNombre()+ "</nombre>"+ "\n<ciudad>" + newRefugio.getCiudad()+ "</ciudad>"+ "\n<abierto>" + newRefugio.isAbierto()+ "</abierto>"+ "\n<sucursales>" + newRefugio.getSucursales() + "</sucursales>"+ "\n</refugio>) into doc('/db/TortuForms/Refugios.xml')/Refugios";
        
        try{
            c = obtenConexion(); 
            XQExpression xqe = c.createExpression(); 
            xqe.executeCommand(addToDB);
        }
        catch (XQException e) { 
            muestraErrorXQuery(e);
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        finally { 
            try { 
                if (c != null) { 
                    c.close(); 
                } 
            } 
            catch (XQException xe) { 
                xe.printStackTrace(); 
            } 
        }

    }
    
    private void modifyOnDB(Refugio modifiedRefugio){
        
        XQConnection c = null; 
        String refugioID = "update value doc('/db/TortuForms/Refugios.xml')/Refugios/refugio[id=" + modifiedRefugio.getId() +"]/id with \"" + modifiedRefugio.getId() + "\"";
        String refugioNombre = "update value doc('/db/TortuForms/Refugios.xml')/Refugios/refugio[id=" + modifiedRefugio.getId() +"]/nombre with \"" + modifiedRefugio.getNombre()+ "\"";
        String refugioCiudad = "update value doc('/db/TortuForms/Refugios.xml')/Refugios/refugio[id=" + modifiedRefugio.getId() +"]/ciudad with \"" + modifiedRefugio.getCiudad()+ "\"";
        String refugioAbierto = "update value doc('/db/TortuForms/Refugios.xml')/Refugios/refugio[id=" + modifiedRefugio.getId() +"]/abierto with \"" + String.valueOf(modifiedRefugio.isAbierto())+ "\"";
        String refugioSucursales = "update value doc('/db/TortuForms/Refugios.xml')/Refugios/refugio[id=" + modifiedRefugio.getId() +"]/sucursales with \"" + modifiedRefugio.getSucursales()+ "\"";

        try{
            c = obtenConexion(); 
            XQExpression xqe = c.createExpression(); 
            xqe.executeCommand(refugioNombre);
            xqe.executeCommand(refugioCiudad);
            xqe.executeCommand(refugioAbierto);
            xqe.executeCommand(refugioSucursales);
            xqe.executeCommand(refugioID);

        }
        catch (XQException e) { 
            muestraErrorXQuery(e);
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        finally { 
            try { 
                if (c != null) { 
                    c.close(); 
                } 
            } 
            catch (XQException xe) { 
                xe.printStackTrace(); 
            } 
        }
        
    }
    
    private void eliminateFromDB(Refugio eliminatedRefugio){
        
        XQConnection c = null; 
        String eliminatefromDB = "update delete doc('/db/TortuForms/Refugios.xml')/Refugios/refugio[id=" + eliminatedRefugio.getId() +"]";
        
        try{
            c = obtenConexion(); 
            XQExpression xqe = c.createExpression(); 
            xqe.executeCommand(eliminatefromDB);
        }
        catch (XQException e) { 
            muestraErrorXQuery(e);
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        finally { 
            try { 
                if (c != null) { 
                    c.close(); 
                } 
            } 
            catch (XQException xe) { 
                xe.printStackTrace(); 
            } 
        }
        
    }
    
    
    //Prueba
    
    
    
    private static String nomClaseDS = "net.xqj.exist.ExistXQDataSource"; 
	
    private static XQConnection obtenConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XQException{
        
        XQDataSource xqs = (XQDataSource) Class.forName(nomClaseDS).newInstance();
        
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        xqs.setProperty("user", "admin");
        xqs.setProperty("password", "Pablo");
        
        return xqs.getConnection();
    }

    private static void muestraErrorXQuery(XQException e){
        System.err.println("XQuery ERROR mensaje: " + e.getMessage());
        System.err.println("XQuery ERROR causa: " + e.getCause());
        System.err.println("XQuery ERROR código: " + e.getVendorCode());
    }

    
}