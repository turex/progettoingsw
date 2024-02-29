package org.progettingsw.OCM;

public class Medico{
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String professione = "";
	//String sesso = "";
	
	public Medico(String nome,String cognome,String ID,String professione) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.professione = professione;
		//this.sesso = sesso;
		
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

	@Override
	public String toString() {
		return "Medico [nome=" + nome + ", cognome=" + cognome + ", ID=" + ID + ", professione=" + professione + "]";
	}
	
	

}
