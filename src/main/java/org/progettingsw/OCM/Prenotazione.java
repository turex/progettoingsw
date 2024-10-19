package org.progettingsw.OCM;

public class Prenotazione {
	
	String id_paziente ="";
	String id_medico ="";
	String professione ="";
	String data="";	
	String priority="";
	int costo=0;


	public Prenotazione(String id_paziente, String id_medico,String professione, String data, String priority,int costo) {
		this.id_paziente = id_paziente;
		this.id_medico = id_medico;
		this.professione = professione;
		this.data = data;
		this.priority = priority;
		this.costo = costo;
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
	
	public String getPriority() {
		// TODO Auto-generated method stub
		return priority;
		
	}
	
	public int getCosto() {
		// TODO Auto-generated method stub
		return costo;
		
	}


	@Override
	public String toString() {
		return "Prenotazione [id_paziente=" + id_paziente + ", id_medico=" + id_medico + ", professione=" + professione
				+ ", data=" + data + ", prioritá=" + priority + ", costo=" + costo + "]";
	}
	
	public String toString1() {
		return id_paziente + ", " + id_medico +  ", " + professione
				+ ", " + data + ", " + priority  +  ", " + costo ;
	}
	

}

