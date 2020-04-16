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
class TortugaModel {
    
    ArrayList <Tortuga> tortugas;
    
    public TortugaModel(){
        tortugas = new ArrayList();

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
    
    public void persistenciaDeDatos(ArrayList<Tortuga> nuevosDatos){

        this.tortugas.clear();
        
        for(Tortuga tortuga: nuevosDatos){
            tortugas.add(tortuga);
        }
        
        this.addDataToDb(nuevosDatos);

    }
    
    /* Aqui ira el Acceso a los datos de formas distintas */
        
    private void addDataToDb(ArrayList<Tortuga> nuevosDatos){
        
        this.generarNuevoFicheroXml(nuevosDatos);
        if(nuevosDatos.size() == 0){
            
            this.tortugas.clear();

        }
    }
    
    private void getDataFromDb(){
        
        this.tortugas = getInfoTortugasFicheroXml();
    }
    
    /*      :_-----_:      */
    
    public static void generarNuevoFicheroXml(ArrayList<Tortuga> tortugas){
        
        DocumentBuilderFactory tortuga = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = tortuga.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Tortugas", null);
            document.setXmlVersion("1.0"); 
            
            
            
            
            for(Tortuga tortugaAux : tortugas) {
                Element raiz = document.createElement("tortuga");
                document.getDocumentElement().appendChild(raiz); 
   
                CrearElemento("id",Integer.toString(tortugaAux.getId()), raiz, document); 

                CrearElemento("apodo",tortugaAux.getApodo(), raiz, document); 

                CrearElemento("peso",Double.toString(tortugaAux.getPeso()), raiz, document); 

                CrearElemento("hiberna",Boolean.toString(tortugaAux.isHiberna()), raiz,document); 
                
                CrearElemento("edad",Integer.toString(tortugaAux.getEdad()), raiz,document); 

                CrearElemento("idReferencia_Cuidador",Integer.toString(tortugaAux.getIdReferencia_Cuidador()), raiz,document); 
            }
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Tortugas.xml"));        
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        }catch (Exception e){
                System.out.println("Exception: " +e);
        }
    }
        
    static void  CrearElemento(String datoTortuga, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoTortuga); 
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }
        
        
        /**/
        
        
        public ArrayList getInfoTortugasFicheroXml(){
        
            try{

                XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
                GestionContenidoXml gestor= new GestionContenidoXml(tortugas);  
                procesadorXML.setContentHandler(gestor);
                InputSource fileXML = new InputSource("Tortugas.xml");	    
                procesadorXML.parse(fileXML);
            }catch (Exception e){
                System.out.println("Excepción: " + e);
            }



            return tortugas;
        }
    
    
        public class GestionContenidoXml extends DefaultHandler {	 


            String nombreElementoActual;
            Tortuga aux;
            ArrayList<Tortuga> tortugasXml;

            public GestionContenidoXml(ArrayList<Tortuga> tortugasXml) {
                super();

                this.tortugasXml = tortugasXml;
                this.nombreElementoActual = new String();
                this.aux = new Tortuga();

            }	    
            public void startDocument() {
            }	    
            public void endDocument() {
            }	 	    
            public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
                nombreElementoActual = nombreC;


            } 	
            public void endElement(String uri, String nombre, String nombreC) {
                if(nombreC.equals("tortuga")){
                   this.tortugasXml.add(aux);
                   aux = new Tortuga();
                }


            }	
            public void characters(char[] ch, int inicio, int longitud){
                String value = new String(ch, inicio, longitud);
                value = value.replaceAll("[\t\n]","");	   

                if(nombreElementoActual.equals("id")){
                    aux.setId(Integer.valueOf(value));
                }else if(nombreElementoActual.equals("apodo")){
                    aux.setApodo(value);
                }else if(nombreElementoActual.equals("peso")){
                    aux.setPeso(Double.valueOf(value));
                }else if(nombreElementoActual.equals("hiberna")){
                    aux.setHiberna(Boolean.valueOf(value));
                }else if(nombreElementoActual.equals("edad")){
                    aux.setEdad(Integer.valueOf(value));
                }else if(nombreElementoActual.equals("idReferencia_Cuidador")){
                    aux.setIdReferencia_Cuidador(Integer.valueOf(value));
                }
            }          
        }
        
}
