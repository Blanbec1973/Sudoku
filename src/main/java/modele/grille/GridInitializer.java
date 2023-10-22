package modele.grille;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class GridInitializer {
	private final Grille grille;
    private static final Logger logger = LogManager.getLogger(GridInitializer.class.getPackage().getName());
	public GridInitializer(Grille grille) {
		this.grille = grille;
	}

    public void init (String nomFichier) {
    	String readLine;
        int valeur;
        int indexCase = 1;
        File monFichier = new File(nomFichier);
        int y=0;
        try (BufferedReader b = new BufferedReader(new FileReader(monFichier))){
            while ((readLine = b.readLine()) != null) {
                for (int x=0;x<9;x++) {
                    valeur = Integer.parseInt(readLine.substring(x,x+1));
                    if (valeur != 0) {
                        grille.getCase(x, y).setCaseInitiale(valeur);
                    }
                    else {
                    	grille.getCasesAtrouver().add(indexCase);
                    }
                    indexCase+=1;
                }
                y++;
            }
        } catch (IOException|NullPointerException ex) {
        	logger.fatal("Exception : {}",ex.getMessage());
        }
        logger.info("Chargement OK fichier : {}",nomFichier);
    }
	
    public void calculTousLesCandidats() {
    	for (Integer caseATrouver : grille.getCasesAtrouver()) {
    		CaseEnCours.setCaseEnCours(caseATrouver);
    		this.calculCandidatsInitiaux(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
    	}
    }
    
    
    private void calculCandidatsInitiaux(int x, int y) {
        for (int valeur=1;valeur<10;valeur++) {
            if (this.checkPresenceValeurLigne(valeur, y)) 
            	{grille.getCase(x, y).elimineCandidat(valeur);}
            if (this.checkPresenceValeurColonne(valeur, x)) 
            	{grille.getCase(x, y).elimineCandidat(valeur);}
            if (this.checkPresenceValeurRegion(valeur)) 
            	{grille.getCase(x, y).elimineCandidat(valeur);}
        }
    }
    
    public boolean checkPresenceValeurLigne(int valeur, int numLigne) {
        for (int i=0;i<9;i++) {
            if (grille.getCase(i, numLigne).getValeur()==valeur) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceValeurColonne(int valeur, int numColonne) {
        for (int i=0;i<9;i++) {
            if (grille.getCase(numColonne, i).getValeur()==valeur) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceValeurRegion(int valeur) {
        for (int x=CaseEnCours.getxRegion();x<CaseEnCours.getxRegion()+3;x++) {
            for (int y=CaseEnCours.getyRegion();y<CaseEnCours.getyRegion()+3;y++) {
                if (grille.getCase(x, y).getValeur() == valeur) {return true;}
            }
        }
        return false;
    }
	
}
