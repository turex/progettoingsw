package org.progettingsw.OCM;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AppTest 
{

	MedicoBuilder a = new MedicoBuilder();
	PazienteBuilder b = new PazienteBuilder();
	PrenotazioneBuilder c = new PrenotazioneBuilder();
	Basket d = Basket.getIstance();
	Pagamento e = new Pagamento();
	Pagamenti strategia;

	@Before
	public void setup() {
		System.out.println("Setup test environment");
		System.out.println("Building paziente");
		b.setNome("Test pa").setCognome("ziente").setSesso("maschio").getPaziente();
		System.out.println("Building medico");
		a.setNome("Test").setCognome("Uno").setID("RAND").setProfessione("Medico").getMedico();
		System.out.println("Building prenotazione");
		c.setidPaziente("ABCDEF5490J").setidMedico("01234567").setPriority("Media").setData("12/02/2025 09:00").setCosto(60).getPrenotazione();
		
	}
	
    
    @Test
    public void testbuild() {
    	System.out.println("Inizio test del building di 'Paziente'");
       	System.out.println("Nome medico : " + b.getPaziente().getNome());
    	assertEquals("Test pa", b.getPaziente().getNome());
    	
    	System.out.println("Inizio test del building di 'Medico'");
    	System.out.println("Nome medico : " + a.getMedico().getNome());
    	assertEquals("Test", a.getMedico().getNome());
    	System.out.println("Inizio test del building di 'Prenotazione'");
    	System.out.println("Nome medico : " + c.getPrenotazione().getidPaziente());
    	assertEquals("ABCDEF5490J", c.getPrenotazione().getidPaziente());
    	d.addToBasket("OCULISTA", "ABCDEF5490J", "MEDIO", "12/02/1980");
    	System.out.println("Totale prima di aver pagato : " + d.getTotale("ABCDEF5490J"));
    	strategia = new pagaPayPal();
    	e.pagato(strategia, "ABCDEF5490J");
    	System.out.println("Totale dopo aver pagato : " + d.getTotale("ABCDEF5490J"));
    	
    	System.out.println("Check esenzione > 65 anni");
    	
    	d.addToBasket("OCULISTA", "ABCDEF5490J55", "ALTO", "12/02/1940");
    }
    
    @After
    public void clean() {
    System.out.println("Cleaning build");	
    d.clearBasket("ABCDEF5490J");
    
    System.out.println("Basket cleaned");	
    }
  
    
    
}
