package org.progettingsw.OCM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	JsonHelper n;
	MedicoBuilder a = new MedicoBuilder();
	static final String PATH = System.getProperty("user.dir");
	static File m = new File(PATH + "\\Medico.json");
	static File p = new File(PATH + "\\Paziente.json");
	

	@Before
	public void setup() {
	  n = JsonHelper.getIstance(); // creo oggetto JSON
	}
	
    @Test
    public void testJson() {
       	n.addtoJson("Ciao", "Ciao", null,null,null,null,null,"Paziente");
		n.addtoJson("Ciao1", "Ciao1", null,null,null,null,null, "Medico");
		n.addtoJson("Ciao12", "Ciao2", null,null,null,null,null, "Medico");
		n.writeJson("Medico");
		n.writeJson("Paziente");
		System.out.println("Struttura json per 'Medico' :");
		n.readJson("Medico");
		n.readJson("Paziente");
		System.out.println("JSon 'Medico' creato correttamente" + " nel path : " + PATH);
		assertTrue(m.exists());
		
    }
    
    @Test
    public void testbuildMedico() {
    	System.out.println("Inizio test del building di 'Medico'");
    	a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
    	System.out.println("Nome medico : " + a.getMedico().getNome());
    	assertEquals("Test", a.getMedico().getNome());
    	
    }
    
    
    @AfterClass
    static public void cleantestJson() {
    	System.out.println("Pulizia del database di prova");
    	if(m.exists())
    	m.delete();
    	if(p.exists())
        	p.delete();
    }
}
