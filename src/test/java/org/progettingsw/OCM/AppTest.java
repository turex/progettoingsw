package org.progettingsw.OCM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	JsonHelper n;
	MedicoBuilder a = new MedicoBuilder();
	final String PATH = System.getProperty("user.dir");
	File f = new File(PATH + "\\Medico.json");
	

	@Before
	public void setup() {
	  n = JsonHelper.getIstance(); // creo oggetto JSON
	}
	
    @Test
    public void testJson() {
       	n.addtoJson("Ciao", "Ciao", "Paziente");
		n.addtoJson("Ciao1", "Ciao1", "Medico");
		n.addtoJson("Ciao12", "Ciao2", "Medico");
		n.writeJson("Medico");
		n.readJson("Medico");
		System.out.println("JSon creato correttamente");
		System.out.println("Nel path : " + PATH);
		System.out.println("Struttura json per 'Medico' :");
		String tt = (String) n.readJson("Medico");
		System.out.println(n.readJson("Medico"));
		assertTrue(f.exists());
		
    }
    
    @Test
    public void testbuildMedico() {
    	a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
    	System.out.println(a.getMedico().getNome());
    	  assertEquals("Test", a.getMedico().getNome());
    	
    }
    
    
    @After
    public void cleantestJson() {
    	System.out.println("Pulizia del database di prova");
    	if(f.exists())
    	f.delete();
    }
}
