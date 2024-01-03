package main;

public class Prenotazione {
	
	String nome_paziente ="";
	String nome_medico ="";
	String professione ="";
	

	public Prenotazione(String nome_paziente, String nome_medico, String professione) {
		this.nome_paziente = nome_paziente;
		this.nome_medico = nome_medico;
		this.professione = professione;
	}
	
	
	
	

}


//Creo il builder 

class PrenotazioneBuilder{
	
	String nome_paziente ="";
	String nome_medico ="";
	String professione ="";
	
	public PrenotazioneBuilder setnomePaziente(String nome_paziente) {
		this.nome_paziente = nome_paziente;
		return this;	
	}
	
	public PrenotazioneBuilder setnomeMedico(String nome_medico) {
		this.nome_medico = nome_medico;
		return this;	
	}
	
	public PrenotazioneBuilder setProfessione(String professione) {
		this.professione = professione;
		return this;	
	}
	
	public Prenotazione getPrenotazione() {
		return new Prenotazione(nome_paziente,nome_medico,professione);
	}
	
}
