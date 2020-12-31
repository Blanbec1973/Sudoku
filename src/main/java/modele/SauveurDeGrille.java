/**
 * 
 */
package modele;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controle;

/**
 * @author heynerr
 *
 */
class SauveurDeGrille {
	private String fileName;
	private static final Logger LOGGER = Logger.getLogger(Controle.class.getPackage().getName());
	/**
	 * @param maGrille Grille de Sudoku à sauvegarder.
	 * 
	 */
	public SauveurDeGrille(Grille maGrille) {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "*.sud", "sud");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    fileName = this.corrigeFileName(chooser.getSelectedFile().getAbsolutePath(), 
                    chooser.getSelectedFile().getName());
	    	this.saveFile(maGrille);
	    }	    
	}
	
	public static void main(String[] args) {
		new SauveurDeGrille(null);
	}
	
	private String corrigeFileName(String absolutePath, String name) {
		if (name.contains("."))
			return absolutePath;
		else
			return absolutePath+".sud";
	}
	
	private void saveFile(Grille grille) {
		String writeLine ="";
		
		try (BufferedWriter b = new BufferedWriter(new FileWriter(fileName))) {
			for (int numCase = 1; numCase < 82; numCase++) {
				writeLine+=String.valueOf(grille.getCase(numCase).getValeur());
				if (numCase % 9 == 0) {
					b.write(writeLine);
					if (numCase != 81) b.newLine();
					writeLine="";
				} 
			}
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE,"Exception : {0}",ex.getMessage());
		}
		
	}	


}
