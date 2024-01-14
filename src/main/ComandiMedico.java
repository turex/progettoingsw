package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class ComandiMedico {
	
	List<Medico> medi = new ArrayList<>();
	
	
	void addMedico(MedicoBuilder m) {
		
		m.ID = m.nome.substring(0,2).toUpperCase() + m.cognome.substring(0, 2).toUpperCase() + m.professione.substring(0,2).toUpperCase();        
		medi.add(m.getMedico());

	}
	
DefaultListModel listaMedicitoString() { // Inserisco in una lista modello le professioni
		
		
		final DefaultListModel model = new DefaultListModel();
		
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
