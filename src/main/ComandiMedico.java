package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class ComandiMedico {
	
	List<Medico> medi = new ArrayList<>();
	
	
	void addMedico(MedicoBuilder m) {
		
		int len_n = m.nome.length();
		int len_c = m.cognome.length();
		
		final int len_nome = (len_n > 5) ? len_n/2 : len_n;
		final int len_cognome = (len_c > 5) ? len_c/2 : len_c;
		
		m.ID = m.nome.substring(0,len_nome).toUpperCase() + m.cognome.substring(0, len_cognome).toUpperCase() + m.professione.substring(0,2).toUpperCase();        
		medi.add(m.getMedico());

	}
	
DefaultListModel<String> listaMedicitoString() { // Inserisco in una lista modello le professioni
		
		
		final DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int list = 0; list < medi.size(); list++) {
			
			model.addElement(medi.get(list).nome + " " + medi.get(list).cognome + " " + medi.get(list).professione);
		} 
		
		
		return model;
		
	}

boolean checkMedico(String nome, String cognome , String professione) {
	
	// True se esiste o ci sono errori
			
	if (nome.isEmpty() || cognome.isEmpty() || professione.isEmpty())
		return true;
	
	
	for (int i = 0; i< medi.size(); i++) {
		String check_nome="";
		String check_cognome="";
		String check_professione="";
		
		
		check_nome = medi.get(i).nome.toUpperCase();
		check_cognome = medi.get(i).cognome.toUpperCase();
		check_professione = medi.get(i).professione.toUpperCase();
		
		if(check_nome.equals(nome.toUpperCase()) && check_cognome.equals(cognome.toUpperCase()) && check_professione.equals(professione.toUpperCase()))
		
			return true;
		
		
	}
		return false;
	
	


}
	
	
	void printMedico(int z) {  
		
		System.out.println(medi.get(z));
	}
	
	void listMedici() {  
		
		for (int list = 0; list < medi.size(); list++) {
		System.out.println(medi.get(list));
		}
	}

}
