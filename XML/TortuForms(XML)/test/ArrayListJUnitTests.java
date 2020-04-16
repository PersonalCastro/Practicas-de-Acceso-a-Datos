/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tortuforms.Cuidador;
import tortuforms.GrupoEspecialista;
import tortuforms.Refugio;
import tortuforms.Tortuga;

/**
 *
 * @author PersonalCastro
 */
public class ArrayListJUnitTests {
    
    public ArrayListJUnitTests() {
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
    public void test_Add_refugio() {
        
        
        ArrayList<Refugio> refugioTestList = new ArrayList();
        
        int preAddSize = refugioTestList.size();
        refugioTestList.add(new Refugio());
        int postAddSize = refugioTestList.size();
        
        Assert.assertNotEquals(preAddSize, postAddSize);
    }
    
    @Test
    public void test_Add_grupoEspecialista() {
        
        
        ArrayList<GrupoEspecialista> grupoEspecialistaTestList = new ArrayList();
        
        int preAddSize = grupoEspecialistaTestList.size();
        grupoEspecialistaTestList.add(new GrupoEspecialista());
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
        
        
        ArrayList<GrupoEspecialista> grupoEspecialistaTestList = new ArrayList();
        grupoEspecialistaTestList.add(new GrupoEspecialista());
        
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
        Assert.assertEquals(abierto, refugioTest.isAbierto());
        Assert.assertEquals(sucursales, refugioTest.getSucursales());
        
    }
    
    @Test
    public void test_Create_grupoEspecialista() {
        
        int id = 1;
        String especialidad = "Prueba_especialidad";
        int capacidad = 2;
        boolean expedicion = false;
        int informes = 3;
        int idReferencia_refugio = 4;
        GrupoEspecialista grupoEspecialistaTest = new GrupoEspecialista(id, especialidad, capacidad, expedicion, informes, idReferencia_refugio);
        
        Assert.assertEquals(id, grupoEspecialistaTest.getId());
        Assert.assertEquals(especialidad, grupoEspecialistaTest.getEspecialidad());
        Assert.assertEquals(capacidad, grupoEspecialistaTest.getCapacidad());
        Assert.assertEquals(expedicion, grupoEspecialistaTest.isExpedicion());
        Assert.assertEquals(informes, grupoEspecialistaTest.getInformes());
        Assert.assertEquals(idReferencia_refugio, grupoEspecialistaTest.getIdReferencia_refugio());

    }
    
    @Test
    public void test_Create_cuidador() {
        
        int id = 1;
        String nombre = "Prueba_Nombre";
        int edad = 2;
        boolean jefe = false;
        String dni = "Prueba_Dni";
        int idReferencia_grupoEspecialista = 3;
        Cuidador cuidador = new Cuidador(id, nombre, edad, jefe, dni, idReferencia_grupoEspecialista);
        
        Assert.assertEquals(id, cuidador.getId());
        Assert.assertEquals(nombre, cuidador.getNombre());
        Assert.assertEquals(edad, cuidador.getEdad());
        Assert.assertEquals(jefe, cuidador.isJefe());
        Assert.assertEquals(dni, cuidador.getDni());
        Assert.assertEquals(idReferencia_grupoEspecialista, cuidador.getIdReferencia_grupoEspecialista());
    }
    
    @Test
    public void test_Create_tortuga() {
        
        int id = 1;
        String apodo = "Prueba_Apodo";
        double peso = 2.5;
        boolean hiberna = false;
        int edad = 3;
        int idReferencia_Cuidador = 4;
        Tortuga tortuga = new Tortuga(id, apodo, peso, hiberna, edad, idReferencia_Cuidador);
        
        Assert.assertEquals(id, tortuga.getId());
        Assert.assertEquals(apodo, tortuga.getApodo());
        System.out.println(peso + " " + tortuga.getPeso());
        Assert.assertEquals(peso, tortuga.getPeso(), 0.1);
        Assert.assertEquals(hiberna, tortuga.isHiberna());
        Assert.assertEquals(edad, tortuga.getEdad());
        Assert.assertEquals(idReferencia_Cuidador, tortuga.getIdReferencia_Cuidador());
    }
    
}
