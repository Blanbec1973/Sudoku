package controleur;

import modele.grille.Grille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class SauveurDeGrille {
	private static final Logger logger = LogManager.getLogger(SauveurDeGrille.class);

	private SauveurDeGrille() {
		throw new IllegalStateException("Utility class");
	}
	  
	public static void saveGrille(Grille grille, String fileName) {

		StringBuilder writeLine = new StringBuilder();
		
		try (BufferedWriter b = new BufferedWriter(new FileWriter(fileName))) {
			for (int numCase = 1; numCase < 82; numCase++) {
				writeLine.append(String.valueOf(grille.getCase(numCase).getValeur()));
				if (numCase % 9 == 0) {
					b.write(writeLine.toString());
					if (numCase != 81) b.newLine();
					writeLine.setLength(0);
				} 
			}
		} catch (IOException ex) {
			logger.fatal("Exception : {}",ex.getMessage());
		}
		
	}	


}
