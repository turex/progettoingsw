package main;

public class MedicoBuilder {
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String professione = "";
	//String sesso = "";
	//String prenotazione = "";

	public MedicoBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public MedicoBuilder setCognome(String cognome) {
		this.cognome = cognome;
		return this;

	}

	public MedicoBuilder setID(String ID) {
		
		
		this.ID = ID;
		return this;

	}

	
	public MedicoBuilder setProfessione(String professione) {
		this.professione = professione;
		return this;

	}
	/*
	public MedicoBuilder setSesso(String sesso) {
		this.sesso = sesso;
		return this;
	}
	
	public MedicoBuilder setPrenotazione(String prenotazione) {
		this.prenotazione = prenotazione;
		return this;
	}
	*/
	
	public Medico getMedico() {
		return new Medico(nome,cognome,ID,professione);
	}
	
	

}
