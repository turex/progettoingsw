package org.progettingsw.OCM;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;


public class AppTest 
{

	MedicoBuilder a = new MedicoBuilder();
	PazienteBuilder b = new PazienteBuilder();
	

	@Before
	public void setup() {
		System.out.println("Setup test environment");
		System.out.println("Building paziente");
		b.setNome("Test pa").setCognome("ziente").setSesso("maschio").getPaziente();
		System.out.println("Building medico");
		a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
		
	}
	
    
    @Test
    public void testbuildMedico() {
    	System.out.println("Inizio test del building di 'Medico'");
    	a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
    	System.out.println("Nome medico : " + a.getMedico().getNome());
    	assertEquals("Test", a.getMedico().getNome());
    	
    }
    
  
    
    
}
