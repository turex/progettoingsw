package org.progettingsw.OCM;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;


public class AppTest 
{

	MedicoBuilder a = new MedicoBuilder();
	PazienteBuilder b = new PazienteBuilder();
	PrenotazioneBuilder c = new PrenotazioneBuilder();

	@Before
	public void setup() {
		System.out.println("Setup test environment");
		System.out.println("Building paziente");
		b.setNome("Test pa").setCognome("ziente").setSesso("maschio").getPaziente();
		System.out.println("Building medico");
		a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
		System.out.println("Building prenotazione");
		
		
	}
	
    
    @Test
    public void testbuild() {
    	System.out.println("Inizio test del building di 'Paziente'");
       	System.out.println("Nome medico : " + b.getPaziente().getNome());
    	assertEquals("Test pa", b.getPaziente().getNome());
    	
    	System.out.println("Inizio test del building di 'Medico'");
    	System.out.println("Nome medico : " + a.getMedico().getNome());
    	assertEquals("Test", a.getMedico().getNome());
    	
    }
    
  
    
    
}
