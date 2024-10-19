package org.progettingsw.OCM;

public class PrenotazioneBuilder{
	
	String id_paziente ="";
	String id_medico ="";
	String professione ="";
	String data="";
	String priority="";
	int costo=0;
	
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
	
	public PrenotazioneBuilder setPriority(String priority) {
		this.priority = priority;
		return this;	
	}
	
	public PrenotazioneBuilder setCosto(int costo) {
		this.costo = costo;
		return this;	
	}
	
	
	public Prenotazione getPrenotazione() {
		return new Prenotazione(id_paziente, id_medico,professione,data,priority,costo);
	}
	
	
	
}
