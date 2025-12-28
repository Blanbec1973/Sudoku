package model.grille;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class InitGrilleService {
    private static final Logger logger = LoggerFactory.getLogger(InitGrilleService.class);

    private InitGrilleService() throws InstantiationException {
        throw new InstantiationException("Utility class : "+this.getClass().getName());
    }
    public static void init(Grille grille, Path path) {
        grille.getCasesAtrouver().clear();
        String readLine;
        int valeur;
        int indexCase = 1;
        int y=0;
        try (BufferedReader b = new BufferedReader(new FileReader(path.toFile()))){
            while ((readLine = b.readLine()) != null) {
                for (int x=0;x<9;x++) {
                    valeur = Integer.parseInt(readLine.substring(x,x+1));
                    if (valeur != 0) {
                        grille.getCase(x,y).setCaseInitiale(valeur);
                    }
                    else {
                        grille.getCasesAtrouver().add(indexCase);
                        grille.getCase(x,y).initialiserCaseVide();
                    }
                    indexCase+=1;
                }
                y++;
            }
        } catch (IOException | NullPointerException ex) {
            logger.error("Exception : {}",ex.getMessage());
            System.exit(-1);
        }
        logger.info("Chargement OK fichier : {}",path.getFileName());
    }
}
