package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class ComandiPaziente {
	
	List<Paziente> pazi = new ArrayList<>();
	
	
	void addPaziente(PazienteBuilder p) {
		
		int len_n = p.nome.length();
		int len_c = p.cognome.length();
		
		final int len_nome = (len_n > 5) ? len_n/2 : len_n;
		final int len_cognome = (len_c > 5) ? len_c/2 : len_c;
		
		p.ID = p.nome.substring(0,len_nome).toUpperCase() + p.cognome.substring(0, len_cognome).toUpperCase() + p.nascita.replace("/", "");
        pazi.add(p.getPaziente());
        

	}
	
	String getID(PazienteBuilder p) {
		return p.getPaziente().getID();
	}
	
	
	void printPaziente(int z) {  
		
		System.out.println(pazi.get(z));
	}
	
	void listPazienti() {  
		
		if(pazi.size() > 0) {
		for (int list = 0; list < pazi.size(); list++) {
		System.out.println(pazi.get(list));
		}
		}
		else
			System.out.println("Lista pazienti vuota");
	}
	
	DefaultListModel<String> listaPazientitoString() {
		
		
		final DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int list = 0; list < pazi.size(); list++) {
			
			model.addElement(pazi.get(list).nome + " " + pazi.get(list).cognome + " " + pazi.get(list).ID);
		}
		
		
		return model;
		
	}
	
	boolean checkPaziente(String nome, String cognome , String nascita) {
		
		// True se esiste o ci sono errori
				
		if (nome.isEmpty() || cognome.isEmpty() || nascita.isEmpty())
			return true;
		
		
		for (int i = 0; i< pazi.size(); i++) {
			String check_nome="";
			String check_cognome="";
			String check_nascita="";
			
			
			check_nome = pazi.get(i).nome.toUpperCase();
			check_cognome = pazi.get(i).cognome.toUpperCase();
			check_nascita = pazi.get(i).nascita.toUpperCase();
			
			if(check_nome.equals(nome.toUpperCase()) && check_cognome.equals(cognome.toUpperCase()) && check_nascita.equals(nascita.toUpperCase()))
			
				return true;
			
			
		}
			return false;
		
		
	

}
	
	
}

