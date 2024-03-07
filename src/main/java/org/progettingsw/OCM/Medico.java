package org.progettingsw.OCM;

import java.util.ArrayList;

public class Medico{
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String professione = "";
	ArrayList<String> dataslot = new ArrayList<>();
	
	public Medico(String nome,String cognome,String ID,String professione, ArrayList<String> dataslot) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.professione = professione;
		this.dataslot.addAll(dataslot);
		
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}


	public String getCognome() {
		// TODO Auto-generated method stub
		return cognome;
	}


	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	public String getProfessione() {
		// TODO Auto-generated method stub
		return professione;
	}
	
	public ArrayList<String> getdataSlot() {
		// TODO Auto-generated method stub
		return dataslot;
	}

	@Override
	public String toString() {
		return "Medico [nome=" + nome + ", cognome=" + cognome + ", ID=" + ID + ", professione=" + professione + "]";
	}
	
	

}
