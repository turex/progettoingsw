package org.progettingsw.OCM;

public class Prenotazione {
	
	String id_paziente ="";
	String id_medico ="";
	String professione ="";
	String data="";	
		

	public Prenotazione(String id_paziente, String id_medico,String professione, String data) {
		this.id_paziente = id_paziente;
		this.id_medico = id_medico;
		this.professione = professione;
		this.data = data;
	}
	
	
	
	public String getidPaziente() {
		// TODO Auto-generated method stub
		return id_paziente;
		
	}
	

	public String getidMedico() {
		// TODO Auto-generated method stub
		return id_medico;
		
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
		return "Prenotazione [id_paziente=" + id_paziente + ", id_medico=" + id_medico + ", professione=" + professione
				+ ", data=" + data + "]";
	}
	

}

