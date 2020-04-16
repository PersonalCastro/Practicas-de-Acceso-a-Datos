/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;


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
class RefugioModel {
    
    ArrayList <Refugio> refugios;
    
    public RefugioModel(){
        refugios = new ArrayList();
        
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

    public void persistenciaDeDatos(ArrayList<Refugio> nuevosDatos){

        this.refugios.clear();
        
        for(Refugio refugio: nuevosDatos){
            refugios.add(refugio);
        }
        
        this.addDataToDb(nuevosDatos);
        
    }    
 
    /* Aqui ira el Acceso a los datos de formas distintas */
         
    private void addDataToDb(ArrayList<Refugio> nuevosDatos){
        
        this.generarNuevoFicheroXml(nuevosDatos);
        if(nuevosDatos.size() == 0){
            
            this.refugios.clear();

        }
    }
    
    private void getDataFromDb(){
        
        this.refugios = getInfoRefugiosFicheroXml();
    }
    
    /*      :_-----_:      */

    
    public static void generarNuevoFicheroXml(ArrayList<Refugio> refugios){
        
        DocumentBuilderFactory tortuga = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = tortuga.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Refugios", null);
            document.setXmlVersion("1.0"); 
            
            
            
            
            for(Refugio refugioAux : refugios) {
                Element raiz = document.createElement("refugio");
                document.getDocumentElement().appendChild(raiz); 
   
                CrearElemento("id",Integer.toString(refugioAux.getId()), raiz, document); 

                CrearElemento("nombre",refugioAux.getNombre(), raiz, document); 

                CrearElemento("ciudad",refugioAux.getCiudad(), raiz, document); 

                CrearElemento("abierto",Boolean.toString(refugioAux.isAbierto()), raiz,document); 
                
                CrearElemento("sucursales",Integer.toString(refugioAux.getSucursales()), raiz,document); 
            }
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Refugios.xml"));        
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        }catch (Exception e){
                System.out.println("Exception: " +e);
        }
    }
        
    static void  CrearElemento(String datoRefugio, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoRefugio); 
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }
   
    
    /**/
    
    public ArrayList getInfoRefugiosFicheroXml(){
        
        try{

            XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
            GestionContenidoXml gestor= new GestionContenidoXml(refugios);  
            procesadorXML.setContentHandler(gestor);
            InputSource fileXML = new InputSource("Refugios.xml");	    
            procesadorXML.parse(fileXML);
        }catch (Exception e){
            System.out.println("Excepción: " + e);
        }


        return refugios;
    }
    
    public class GestionContenidoXml extends DefaultHandler {	 


        String nombreElementoActual;
        Refugio aux;
        ArrayList<Refugio> refugiosXml;

        public GestionContenidoXml(ArrayList<Refugio> refugiosXml) {
            super();

            this.refugiosXml = refugiosXml;
            this.nombreElementoActual = new String();
            this.aux = new Refugio();

        }	    
        public void startDocument() {
        }	    
        public void endDocument() {
        }	 	    
        public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
            nombreElementoActual = nombreC;


        } 	
        public void endElement(String uri, String nombre, String nombreC) {
            if(nombreC.equals("refugio")){
               this.refugiosXml.add(aux);
               aux = new Refugio();
            }


        }	
        public void characters(char[] ch, int inicio, int longitud){
            String value = new String(ch, inicio, longitud);
            value = value.replaceAll("[\t\n]","");	   

            if(nombreElementoActual.equals("id")){
                aux.setId(Integer.valueOf(value));
            }else if(nombreElementoActual.equals("nombre")){
                aux.setNombre(value);
            }else if(nombreElementoActual.equals("ciudad")){
                aux.setCiudad(value);
            }else if(nombreElementoActual.equals("abierto")){
                aux.setAbierto(Boolean.valueOf(value));
            }else if(nombreElementoActual.equals("sucursales")){
                aux.setSucursales(Integer.valueOf(value));
            }
        }          
    }

}