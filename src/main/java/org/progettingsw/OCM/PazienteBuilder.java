package org.progettingsw.OCM;

public class PazienteBuilder {
	
	
	String nome = "";
	String cognome = "";
	String ID = "";
	String nascita = "";
	String sesso = "";

	public PazienteBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public PazienteBuilder setCognome(String cognome) {
		this.cognome = cognome;
		return this;

	}

	public PazienteBuilder setID(String ID) {
		
		
		this.ID = ID;
		return this;

	}

	public PazienteBuilder setNascita(String nascita) {
		this.nascita = nascita;
		return this;

	}
	
	public PazienteBuilder setSesso(String sesso) {
		this.sesso = sesso;
		return this;
	}
	
	
	public Paziente getPaziente() {
		return new Paziente(nome,cognome,ID,nascita,sesso);
	}
	
	

}
