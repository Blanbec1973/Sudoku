package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitialiseurDeGrille {
	private Grille grille;
	public InitialiseurDeGrille(Grille grille) {
		this.grille = grille;
	}

    public void init (String nomFichier)  {
        String readLine;
        int valeur;
        int indexCase = 1;
        File monFichier = new File(nomFichier);
        int y=0;
        try {
            BufferedReader b = new BufferedReader(new FileReader(monFichier));
            while ((readLine = b.readLine()) != null) {
                //System.out.println("Ligne :"+y+" : "+ readLine);
                for (int x=0;x<9;x++) {
                    //System.out.println(readLine.substring(x,x+1));
                    valeur = Integer.parseInt(readLine.substring(x,x+1));
                    //System.out.println("xy="+x+y);
                    if (valeur != 0) {
                        grille.getCase(x, y).setValeurCase(valeur);
                        grille.getCase(x, y).setCaseInitiale();
                    }
                    else {
                    	grille.getCasesAtrouver().add(indexCase);
                    }
                    indexCase+=1;
                }
                y++;
            }
            b.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        //this.displayCasesAtrouver();
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
