package main;

public class Paziente implements Uomo{
	
	String nome = "";
	String cognome = "";
	int ID = 0;
	int eta = 0;
	String nascita = "";
	String sesso = "";
	
public Paziente(String nome,String cognome,int ID,int eta,String nascita,String sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.ID = ID;
		this.eta = eta;
		this.nascita = nascita;
		this.sesso = sesso;
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public String getCognome() {
		// TODO Auto-generated method stub
		return cognome;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public int getEta() {
		// TODO Auto-generated method stub
		return eta;
	}

	@Override
	public String getNascita() {
		// TODO Auto-generated method stub
		return nascita;
	}

	@Override
	public String toString() {
		return "Paziente [nome=" + nome + ", cognome=" + cognome + ", ID=" + ID + ", eta=" + eta + ", nascita="
				+ nascita + ", sesso=" + sesso + "]";
	}

	
	
}
