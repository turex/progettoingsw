package org.progettingsw.OCM;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	JsonHelper n;
	
	

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
		
			
    }
}
