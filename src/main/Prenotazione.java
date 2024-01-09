package main;

public class Prenotazione {
	
	String nome_paziente ="";
	String nome_medico ="";
	String professione ="";
	String data="";
	

	public Prenotazione(String nome_paziente, String nome_medico, String professione, String data) {
		this.nome_paziente = nome_paziente;
		this.nome_medico = nome_medico;
		this.professione = professione;
		this.data = data;
	}
	
	
	
	public String getNomePaziente() {
		// TODO Auto-generated method stub
		return nome_paziente;
		
	}
	
	public String getNomeMedico() {
		// TODO Auto-generated method stub
		return nome_medico;
		
	}
	
	public String getProfessione() {
		// TODO Auto-generated method stub
		return professione;
		
	}
	
	public String getData() {
		// TODO Auto-generated method stub
		return data;
		
	}



	@Override
	public String toString() {
		return "Prenotazione [nome_paziente=" + nome_paziente + ", nome_medico=" + nome_medico + ", professione="
				+ professione + ", data=" + data + "]";
	}
	
		

}


//Creo il builder 

class PrenotazioneBuilder{
	
	String nome_paziente ="";
	String nome_medico ="";
	String professione ="";
	String data="";
	
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
	
	public PrenotazioneBuilder setData(String data) {
		this.data = data;
		return this;	
	}
	
	public Prenotazione getPrenotazione() {
		return new Prenotazione(nome_paziente,nome_medico,professione,data);
	}
	
	
	
}
