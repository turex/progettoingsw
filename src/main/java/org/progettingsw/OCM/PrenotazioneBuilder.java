package org.progettingsw.OCM;

public class PrenotazioneBuilder{
	
	String id_paziente ="";
	String id_medico ="";
	String professione ="";
	String data="";
	
	
	public PrenotazioneBuilder setidPaziente(String id_paziente) {
		this.id_paziente = id_paziente;
		return this;	
	}
	
	
	public PrenotazioneBuilder setidMedico(String id_medico) {
		this.id_medico = id_medico;
		return this;	
	}
	
	public PrenotazioneBuilder setProfessione(String professione) {
		this.professione = professione;
		return this;	
	}
	
	public PrenotazioneBuilder setData(String data) {
		this.data = data;
		return this;	
	}
	
	public Prenotazione getPrenotazione() {
		return new Prenotazione(id_paziente, id_medico,professione,data);
	}
	
	
	
}
