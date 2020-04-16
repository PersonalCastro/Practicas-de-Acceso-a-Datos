/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tortuforms.HibernateUtil;
import tortuforms.RefugioModel;
import tortuforms_hibernate.Cuidador;
import tortuforms_hibernate.Grupoespecialista;
import tortuforms_hibernate.Refugio;
import tortuforms_hibernate.Tortuga;


/**
 *
 * @author PersonalCastro
 */
public class ArrayListJUnitTests {
    
    public Refugio refugio_database;

    public ArrayListJUnitTests() {
        refugio_database = new Refugio(10,"Pablos","España",false, 0);

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    
    

    
    @Test
    public void ty_database_add() {
        
        int preAddSize = 0;
                
        int postAddSize = 0;
        
        
        ArrayList<Refugio> refugiosDb = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        preAddSize = refugiosDb.size();
        
        
        RefugioModel a = new RefugioModel();
        
        ArrayList<Refugio> aux = new ArrayList();
        aux.add(refugio_database);
        
        a.persistenciaDeDatos(aux,0,1);
        
        
                ArrayList<Refugio> refugiosDb2 = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb2.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        postAddSize = refugiosDb2.size();
        
        
        Assert.assertEquals(preAddSize, postAddSize -1);
        
                
        ArrayList<Refugio> refugiosDb1 = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb1.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        preAddSize = refugiosDb1.size();
        
        RefugioModel a1 = new RefugioModel();
        
        ArrayList<Refugio> aux1 = new ArrayList();
        aux1.add(refugio_database);
        
        a1.persistenciaDeDatos(aux1,0,3);
        
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (refugio_database != null) {
                s.delete(refugio_database);
                s.refresh(refugio_database);
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
        
        ArrayList<Refugio> refugiosDb3 = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb3.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        postAddSize = refugiosDb3.size();
    }
    
  
    
    
    @Test
    public void test_Add_refugio() {
        
        
        ArrayList<Refugio> refugioTestList = new ArrayList();
        
        int preAddSize = refugioTestList.size();
        refugioTestList.add(new Refugio());
        int postAddSize = refugioTestList.size();
        
        Assert.assertNotEquals(preAddSize, postAddSize);
    }
    
    @Test
    public void test_Add_grupoEspecialista() {
        
        
        ArrayList<Grupoespecialista> grupoEspecialistaTestList = new ArrayList();
        
        int preAddSize = grupoEspecialistaTestList.size();
        grupoEspecialistaTestList.add(new Grupoespecialista());
        int postAddSize = grupoEspecialistaTestList.size();
        
        Assert.assertNotEquals(preAddSize, postAddSize);
    }
    
    @Test
    public void test_Add_cuidador() {
        
        
        ArrayList<Cuidador> cuidadorTestList = new ArrayList();
        
        int preAddSize = cuidadorTestList.size();
        cuidadorTestList.add(new Cuidador());
        int postAddSize = cuidadorTestList.size();
        
        Assert.assertNotEquals(preAddSize, postAddSize);
    }
    
    @Test
    public void test_Add_tortuga() {
        
        
        ArrayList<Tortuga> tortugaTestList = new ArrayList();
        
        int preAddSize = tortugaTestList.size();
        tortugaTestList.add(new Tortuga());
        int postAddSize = tortugaTestList.size();
        
        Assert.assertNotEquals(preAddSize, postAddSize);
    }
    
 /**/   
    
    @Test
    public void test_Clear_refugio() {
        
        
        ArrayList<Refugio> refugioTestList = new ArrayList();
        refugioTestList.add(new Refugio());
        
        refugioTestList.clear();
        int postClearSize = refugioTestList.size();
        
        Assert.assertEquals(0, postClearSize);
    }
    
    @Test
    public void test_Clear_grupoEspecialista() {
        
        
        ArrayList<Grupoespecialista> grupoEspecialistaTestList = new ArrayList();
        grupoEspecialistaTestList.add(new Grupoespecialista());
        
        grupoEspecialistaTestList.clear();
        int postClearSize = grupoEspecialistaTestList.size();
        
        Assert.assertEquals(0, postClearSize);
    }
    
    @Test
    public void test_Clear_cuidador() {
        
        
        ArrayList<Cuidador> cuidadorTestList = new ArrayList();
        cuidadorTestList.add(new Cuidador());
        
        cuidadorTestList.clear();
        int postClearSize = cuidadorTestList.size();
        
        Assert.assertEquals(0, postClearSize);
    }
    
    @Test
    public void test_Clear_tortuga() {
        
        
        ArrayList<Tortuga> tortugaTestList = new ArrayList();
        tortugaTestList.add(new Tortuga());
        
        tortugaTestList.clear();
        int postClearSize = tortugaTestList.size();
        
        Assert.assertEquals(0, postClearSize);
    }
    
/**/

    @Test
    public void test_Create_refugio() {
        
        int id = 1;
        String nombre = "Prueba_nombre";
        String ciudad = "Prueba_ciudad";
        boolean abierto = false;
        int sucursales = 0;
        Refugio refugioTest = new Refugio(id, nombre, ciudad, abierto, sucursales);

        Assert.assertEquals(id, refugioTest.getId());
        Assert.assertEquals(nombre, refugioTest.getNombre());
        Assert.assertEquals(ciudad, refugioTest.getCiudad());
        Assert.assertEquals(abierto, refugioTest.getAbierto());
        
    }
    
    @Test
    public void test_Create_grupoEspecialista() {
        
        int id = 1;
        String especialidad = "Prueba_especialidad";
        int capacidad = 2;
        boolean expedicion = false;
        int informes = 3;
        Grupoespecialista grupoEspecialistaTest = new Grupoespecialista(id, especialidad, capacidad, expedicion, informes, new Refugio());
        
        Assert.assertEquals(id, grupoEspecialistaTest.getId());
        Assert.assertEquals(especialidad, grupoEspecialistaTest.getEspecialidad());
        Assert.assertEquals(expedicion, grupoEspecialistaTest.getExpedicion());

    }
    
    @Test
    public void test_Create_cuidador() {
        
        int id = 1;
        String nombre = "Prueba_Nombre";
        int edad = 2;
        boolean jefe = false;
        String dni = "Prueba_Dni";
        Cuidador cuidador = new Cuidador(id, nombre, edad, jefe, dni, new Grupoespecialista());
        
        Assert.assertEquals(id, cuidador.getId());
        Assert.assertEquals(nombre, cuidador.getNombre());
        Assert.assertEquals(jefe, cuidador.getJefe());
        Assert.assertEquals(dni, cuidador.getDni());
    }
    
    @Test
    public void test_Create_tortuga() {
        
        int id = 1;
        String apodo = "Prueba_Apodo";
        double peso = 2;
        boolean hiberna = false;
        int edad = 3;
        Tortuga tortuga = new Tortuga(id, apodo, Long.valueOf(3) , hiberna, edad, new Cuidador());
        
        Assert.assertEquals(id, tortuga.getId());
        Assert.assertEquals(apodo, tortuga.getApodo());
        System.out.println(peso + " " + tortuga.getPeso());
        Assert.assertEquals(hiberna, tortuga.getHiberna());
    }
    
    
            @Test
    public void ty_database_eliminate() {
        
                
        int preAddSize = 0;
                
        int postAddSize = 0;
        
        
        ArrayList<Refugio> refugiosDb = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        preAddSize = refugiosDb.size();
        
        
        RefugioModel a = new RefugioModel();
        
        ArrayList<Refugio> aux = new ArrayList();
        aux.add(refugio_database);
        
        a.persistenciaDeDatos(aux,0,1);
        
        
                ArrayList<Refugio> refugiosDb2 = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb2.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        postAddSize = refugiosDb2.size();
        
        
        
        ArrayList<Refugio> refugiosDb1 = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb1.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        preAddSize = refugiosDb1.size();
        
        RefugioModel a1 = new RefugioModel();
        
        ArrayList<Refugio> aux1 = new ArrayList();
        aux1.add(refugio_database);
        
        a1.persistenciaDeDatos(aux1,0,3);
        
        Transaction t = null; 
        try { 
            Session s = HibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (refugio_database != null) {
                s.delete(refugio_database);
                s.refresh(refugio_database);
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
        
        ArrayList<Refugio> refugiosDb3 = new ArrayList();
        try{

            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = (Query) s.createQuery( "FROM Refugio WHERE id > 0" ); 

            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id > 0" ).list();
            for (Refugio axuRefugio: listaDep) { 

                refugiosDb3.add(axuRefugio);
            }
            
            s.close();

        }catch(Exception e){
            System.out.println("Exceptions: "+ e);
        }
        
        postAddSize = refugiosDb3.size();
        
        
        Assert.assertEquals(preAddSize, postAddSize +1 );
    }
    
}
