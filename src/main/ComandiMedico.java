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
	
	
	void printMedico(int z) {  
		
		System.out.println(medi.get(z));
	}
	
	void listMedici() {  
		
		for (int list = 0; list < medi.size(); list++) {
		System.out.println(medi.get(list));
		}
	}

}
