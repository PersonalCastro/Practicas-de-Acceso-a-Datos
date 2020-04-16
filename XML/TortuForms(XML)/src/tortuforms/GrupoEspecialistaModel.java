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
class GrupoEspecialistaModel {
   
    ArrayList <GrupoEspecialista> gruposEspecialistas;

    public GrupoEspecialistaModel(){
        gruposEspecialistas = new ArrayList();
        
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
    
    public void persistenciaDeDatos(ArrayList<GrupoEspecialista> nuevosDatos){
        
        this.gruposEspecialistas.clear();
        
        for(GrupoEspecialista grupoEspecialista: nuevosDatos){
            gruposEspecialistas.add(grupoEspecialista);
        }
        
        this.addDataToDb(nuevosDatos);
        
    }  
    
    /* Aqui ira el Acceso a los datos de formas distintas */
  
    private void addDataToDb(ArrayList<GrupoEspecialista> nuevosDatos){        
        
        this.generarNuevoFicheroXml(nuevosDatos);
        if(nuevosDatos.size() == 0){
            
            this.gruposEspecialistas.clear();

        }
    }
    
    private void getDataFromDb(){
        
        this.gruposEspecialistas = getInfoGrupoEspecialistasFicheroXml();
    }
    
 /*      :_-----_:      */

    
    public static void generarNuevoFicheroXml(ArrayList<GrupoEspecialista> gruposEspecialistas){
        
        DocumentBuilderFactory tortuga = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = tortuga.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "GruposEspecialistas", null);
            document.setXmlVersion("1.0"); 
            
            
            
            
            for(GrupoEspecialista grupoEspecialistaAux : gruposEspecialistas) {
                Element raiz = document.createElement("grupoEspecialista");
                document.getDocumentElement().appendChild(raiz); 
   
                CrearElemento("id",Integer.toString(grupoEspecialistaAux.getId()), raiz, document); 

                CrearElemento("especialidad",grupoEspecialistaAux.getEspecialidad(), raiz, document); 

                CrearElemento("capacidad",Integer.toString(grupoEspecialistaAux.getCapacidad()), raiz, document); 

                CrearElemento("expedicion",Boolean.toString(grupoEspecialistaAux.isExpedicion()), raiz,document); 
                
                CrearElemento("informes",Integer.toString(grupoEspecialistaAux.getInformes()), raiz,document); 

                CrearElemento("idReferencia_refugio",Integer.toString(grupoEspecialistaAux.getIdReferencia_refugio()), raiz,document); 
            }
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("GruposEspecialistas.xml"));        
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        }catch (Exception e){
                System.out.println("Exception: " +e);
        }
    }
        
    static void  CrearElemento(String datoGrupoEspecialista, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoGrupoEspecialista); 
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }
   
    
    /**/
    
    
    public ArrayList getInfoGrupoEspecialistasFicheroXml(){
        
        try{

            XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
            GestionContenidoXml gestor= new GestionContenidoXml(gruposEspecialistas);  
            procesadorXML.setContentHandler(gestor);
            InputSource fileXML = new InputSource("GruposEspecialistas.xml");	    
            procesadorXML.parse(fileXML);
        }catch (Exception e){
            System.out.println("Excepción: " + e);
        }


        return gruposEspecialistas;
    }
    
    public class GestionContenidoXml extends DefaultHandler {	 


        String nombreElementoActual;
        GrupoEspecialista aux;
        ArrayList<GrupoEspecialista> gruposEspecialistasXml;

        public GestionContenidoXml(ArrayList<GrupoEspecialista> gruposEspecialistasXml) {
            super();

            this.gruposEspecialistasXml = gruposEspecialistasXml;
            this.nombreElementoActual = new String();
            this.aux = new GrupoEspecialista();

        }	    
        public void startDocument() {
        }	    
        public void endDocument() {
        }	 	    
        public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
            nombreElementoActual = nombreC;


        } 	
        public void endElement(String uri, String nombre, String nombreC) {
            if(nombreC.equals("grupoEspecialista")){
               this.gruposEspecialistasXml.add(aux);
               aux = new GrupoEspecialista();
            }


        }	
        public void characters(char[] ch, int inicio, int longitud){
            String value = new String(ch, inicio, longitud);
            value = value.replaceAll("[\t\n]","");	   

            if(nombreElementoActual.equals("id")){
                aux.setId(Integer.valueOf(value));
            }else if(nombreElementoActual.equals("especialidad")){
                aux.setEspecialidad(value);
            }else if(nombreElementoActual.equals("capacidad")){
                aux.setCapacidad(Integer.valueOf(value));
            }else if(nombreElementoActual.equals("expedicion")){
                aux.setExpedicion(Boolean.valueOf(value));
            }else if(nombreElementoActual.equals("informes")){
                aux.setInformes(Integer.valueOf(value));
            }else if(nombreElementoActual.equals("idReferencia_refugio")){
                aux.setIdReferencia_refugio(Integer.valueOf(value));
            }
        }          
    }
}
