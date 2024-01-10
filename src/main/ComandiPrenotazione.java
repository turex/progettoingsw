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
	
	void listPrenotazioni(String nome_paziente) {  
		
		for (int list = 0; list < pren.size(); list++) {
			String check_nome="";
			check_nome = pren.get(list).nome_paziente.toUpperCase();
			
			if(check_nome.equals(nome_paziente.toUpperCase()))
				System.out.println(pren.get(list));
			
		}
	}


}
