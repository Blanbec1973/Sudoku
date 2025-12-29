package control.eventmanager;

import model.grille.Grille;
import model.grille.GrilleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class GridSaverService {
	private static final Logger logger = LoggerFactory.getLogger(GridSaverService.class);
	  
	public void saveGrid(Grille grille, String fileName) {

		StringBuilder writeLine = new StringBuilder();
		
		try (BufferedWriter b = new BufferedWriter(new FileWriter(fileName))) {
			for (int numCase = 1; numCase < 82; numCase++) {
				writeLine.append(GrilleUtils.getValeurCase(grille, numCase));
				if (numCase % 9 == 0) {
					b.write(writeLine.toString());
					if (numCase != 81) b.newLine();
					writeLine.setLength(0);
				} 
			}
		} catch (IOException ex) {
			logger.error("Exception : {}",ex.getMessage());
		}
		
	}	


}
