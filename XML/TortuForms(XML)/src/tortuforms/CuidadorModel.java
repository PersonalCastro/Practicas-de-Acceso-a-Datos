/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortuforms;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
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
    
    ArrayList <Cuidador> cuidadores;
    
    public CuidadorModel(){
        cuidadores = new ArrayList();

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
    
    public void persistenciaDeDatos(ArrayList<Cuidador> nuevosDatos){

        this.cuidadores.clear();
        
        for(Cuidador cuidador: nuevosDatos){
            cuidadores.add(cuidador);
        }
        
        this.addDataToDb(nuevosDatos);
        
    }
    
    
    /* Aqui ira el Acceso a los datos de formas distintas */
    
    private void addDataToDb(ArrayList<Cuidador> nuevosDatos){
        
        this.generarNuevoFicheroXml(nuevosDatos);
        if(nuevosDatos.size() == 0){
            
            this.cuidadores.clear();

        }
    }
    
    private void getDataFromDb(){
        
        this.cuidadores = getInfoCuidadoresFicheroXml();
    }
    
    
/*      :_-----_:      */

    
    public static void generarNuevoFicheroXml(ArrayList<Cuidador> cuidadores){
        
        DocumentBuilderFactory tortuga = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = tortuga.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Cuidadores", null);
            document.setXmlVersion("1.0"); 
            
            
            
            
            for(Cuidador cuidadoraAux : cuidadores) {
                Element raiz = document.createElement("cuidador");
                document.getDocumentElement().appendChild(raiz); 
   
                CrearElemento("id",Integer.toString(cuidadoraAux.getId()), raiz, document); 

                CrearElemento("nombre",cuidadoraAux.getNombre(), raiz, document); 

                CrearElemento("edad",Integer.toString(cuidadoraAux.getEdad()), raiz, document); 

                CrearElemento("jefe",Boolean.toString(cuidadoraAux.isJefe()), raiz,document); 
                
                CrearElemento("dni",cuidadoraAux.getDni(), raiz,document); 

                CrearElemento("idReferencia_grupoEspecialista",Integer.toString(cuidadoraAux.getIdReferencia_grupoEspecialista()), raiz,document); 
            }
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Cuidadores.xml"));        
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        }catch (Exception e){
                System.out.println("Exception: " +e);
        }
    }
        
    static void  CrearElemento(String datoCuidador, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoCuidador); 
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }
   
    
    /**/
    
        public ArrayList getInfoCuidadoresFicheroXml(){
        
        try{

            XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
            GestionContenidoXml gestor= new GestionContenidoXml(cuidadores);  
            procesadorXML.setContentHandler(gestor);
            InputSource fileXML = new InputSource("Cuidadores.xml");	    
            procesadorXML.parse(fileXML);
        }catch (Exception e){
            System.out.println("Excepción: " + e);
        }


        return cuidadores;
    }
    
    public class GestionContenidoXml extends DefaultHandler {	 


        String nombreElementoActual;
        Cuidador aux;
        ArrayList<Cuidador> cuidadoresXml;

        public GestionContenidoXml(ArrayList<Cuidador> cuidadoresXml) {
            super();

            this.cuidadoresXml = cuidadoresXml;
            this.nombreElementoActual = new String();
            this.aux = new Cuidador();

        }	    
        public void startDocument() {
        }	    
        public void endDocument() {
        }	 	    
        public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
            nombreElementoActual = nombreC;


        } 	
        public void endElement(String uri, String nombre, String nombreC) {
            if(nombreC.equals("cuidador")){
               this.cuidadoresXml.add(aux);
               aux = new Cuidador();
            }


        }	
        public void characters(char[] ch, int inicio, int longitud){
            String value = new String(ch, inicio, longitud);
            value = value.replaceAll("[\t\n]","");	   

            if(nombreElementoActual.equals("id")){
                aux.setId(Integer.valueOf(value));
            }else if(nombreElementoActual.equals("nombre")){
                aux.setNombre(value);
            }else if(nombreElementoActual.equals("edad")){
                aux.setEdad(Integer.valueOf(value));
            }else if(nombreElementoActual.equals("jefe")){
                aux.setJefe(Boolean.valueOf(value));
            }else if(nombreElementoActual.equals("dni")){
                aux.setDni(value);
            }else if(nombreElementoActual.equals("idReferencia_grupoEspecialista")){
                aux.setIdReferencia_grupoEspecialista(Integer.valueOf(value));
            }
        }          
    }
    
}
