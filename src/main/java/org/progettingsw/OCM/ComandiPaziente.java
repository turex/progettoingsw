package org.progettingsw.OCM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;

public class ComandiPaziente {
	
	List<Paziente> pazi = new ArrayList<>();
	PazienteBuilder p;
	
	
	public void addPaziente(PazienteBuilder p) {
		
		p.ID = assignID(p);
        pazi.add(p.getPaziente());
        

	}
	
	public String assignID(PazienteBuilder p) {
		
		String ID = "";
		
		int len_n = p.nome.length();
		int len_c = p.cognome.length();
		
		final int len_nome = (len_n > 5) ? len_n/2 : len_n;
		final int len_cognome = (len_c > 5) ? len_c/2 : len_c;
		
		
		ID = p.nome.substring(0,len_nome).toUpperCase() + p.cognome.substring(0, len_cognome).toUpperCase() + p.nascita.replace("/", "");		
		
		return ID;
	}
	

	public void printPaziente(int z) {  
		
		System.out.println(pazi.get(z));
	}
	
	public void listPazienti() {  
		
		if(pazi.size() > 0) {
		for (int list = 0; list < pazi.size(); list++) {
		System.out.println(pazi.get(list));
		}
		}
		else
			System.out.println("Lista pazienti vuota");
	}
	
	public String listaPazientitoModel() {
		
		String model = "";
				
		for (int list = 0; list < pazi.size(); list++) {
			
			model = pazi.get(list).nome + " " + pazi.get(list).cognome + " " + pazi.get(list).ID;
		}
				
		return model;
		
	}
	
	public boolean checkPaziente(String nome, String cognome , String nascita) {
		
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

