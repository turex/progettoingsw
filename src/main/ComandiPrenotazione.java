package main;

import java.util.ArrayList;
import java.util.List;

public class ComandiPrenotazione {
	
	List<Prenotazione> pren = new ArrayList<>();
	
	
	void addPrenotazione(PrenotazioneBuilder p) {       
		pren.add(p.getPrenotazione());

	}
	
	
	void printMedico(int z) {  
		
		//System.out.println(medi.get(z));
	}
	
	void listPrenotazioni() {  
		
		for (int list = 0; list < pren.size(); list++) {
		System.out.println(pren.get(list));
		}
	}


}
