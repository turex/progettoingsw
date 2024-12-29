package org.progettingsw.OCM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

	MedicoBuilder a = new MedicoBuilder();
	

	@Before
	public void setup() {
	}
	
    
    @Test
    public void testbuildMedico() {
    	System.out.println("Inizio test del building di 'Medico'");
    	a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
    	System.out.println("Nome medico : " + a.getMedico().getNome());
    	assertEquals("Test", a.getMedico().getNome());
    	
    }
    
  
    
    
}
