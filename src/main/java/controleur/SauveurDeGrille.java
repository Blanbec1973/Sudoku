/**
 * 
 */
package controleur;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import modele.Grille;

/**
 * @author heynerr
 *
 */
class SauveurDeGrille {
	private static final Logger LOGGER = Logger.getLogger(Controle.class.getPackage().getName());
	/**
	 * @param maGrille Grille de Sudoku à sauvegarder.
	 * 
	 */
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
			LOGGER.log(Level.SEVERE,"Exception : {0}",ex.getMessage());
		}
		
	}	


}
