package org.progettingsw.OCM;

import java.util.Random;

public class CommonCommand {
	
	public String generateRandomString(int length) {
        // Caratteri validi per la stringa casuale
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        // Creazione di un oggetto Random
        Random random = new Random();

        // Generazione della stringa casuale di lunghezza specificata
        for (int i = 0; i < length; i++) {
            // Generazione di un indice casuale per selezionare un carattere dalla stringa dei caratteri validi
            int randomIndex = random.nextInt(characters.length());
            // Aggiunta del carattere casuale alla stringa casuale
            randomString.append(characters.charAt(randomIndex));
        }

        // Restituzione della stringa casuale generata
        return randomString.toString();
    }
	
	
	public StringBuilder buildString(String... a) {
		StringBuilder componi = new StringBuilder();
		
		 for (String str : a) {
		        componi.append(str);
		    }
		 
		return componi;
		
	}

}
