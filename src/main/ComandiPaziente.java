package main;

import java.util.ArrayList;
import java.util.List;

public class ComandiPaziente {
	
	List<Paziente> pazi = new ArrayList<>();
	
	
	void addPaziente(PazienteBuilder p) {
        pazi.add(p.getPaziente());

        //System.out.println(users.get(2)); TODO : da eliminare, solo per reference
	}
	
	
	void printPaziente(int z) {  
		
		System.out.println(pazi.get(z));
	}
	
	void listPazienti() {  
		
		for (int list = 0; list < pazi.size(); list++) {
		System.out.println(pazi.get(list));
		}
	}

}
