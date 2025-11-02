package model.grille;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Grille {
    private final Case[][] mesCases = new Case [9][9];
    private static final Logger logger = LogManager.getLogger(Grille.class.getPackage().getName());
    private final List<Integer> casesAtrouver= new ArrayList<>();
        
    public Grille() {
        int numcase = 0;
        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                numcase = numcase +1;
                mesCases[x][y] = new Case(numcase, x, y);
            }
        }
    }

    public void init(Path path) {
        casesAtrouver.clear();
        String readLine;
        int valeur;
        int indexCase = 1;
        int y=0;
        try (BufferedReader b = new BufferedReader(new FileReader(path.toFile()))){
            while ((readLine = b.readLine()) != null) {
                for (int x=0;x<9;x++) {
                    valeur = Integer.parseInt(readLine.substring(x,x+1));
                    if (valeur != 0) {
                        this.getCase(x,y).setCaseInitiale(valeur);
                    }
                    else {
                        this.getCasesAtrouver().add(indexCase);
                        this.getCase(x,y).initialiserCaseVide();
                    }
                    indexCase+=1;
                }
                y++;
            }
        } catch (IOException | NullPointerException ex) {
            logger.fatal("Exception : {}",ex.getMessage());
            System.exit(-1);
        }
        logger.info("Chargement OK fichier : {}",path.getFileName());
        calculTousLesCandidats();
    }

    public Case getCase(int x, int y) {return mesCases[x][y];}
    public Case getCase(int numCase) {return mesCases[Utils.calculXsearch(numCase)][Utils.calculYsearch(numCase)];}
    public Case getCaseEnCours() {return mesCases[CaseEnCours.getX()][CaseEnCours.getY()];}
    public List<Integer> getCasesAtrouver() {return casesAtrouver;}
    public CandidatsCase getCandidats(int numCase) {return this.getCase(numCase).getCandidats();}
    public void setAllCandidatsToFalse(int numCase) {this.getCase(numCase).getCandidats().setAllCandidatsToFalse();}
    public void setCandidat(int numCase, int candidat) {this.getCase(numCase).getCandidats().setCandidat(candidat);}
    public void elimineCandidat (int numCase, int candidatAEliminer) {
        this.getCase(numCase).elimineCandidat(candidatAEliminer);
    }
    public boolean isCaseInitiale(int numCase) {return this.getCase(numCase).isCaseInitiale();}
    public boolean isCaseTrouvee(int numCase) {return this.getCase(numCase).isCaseTrouvee();}
    public boolean isCaseATrouver(int numCase) {
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        return isCaseATrouver(x, y);
    }
    public boolean isCaseATrouver(int x, int y) {return this.getCase(x,y).isCaseATrouver();}
    public boolean nEstPasCaseInitiale(int numCase) {return this.getCase(numCase).nEstPasCaseInitiale();}
    public boolean nEstPasCaseTrouvee(int numCase) {return this.getCase(numCase).nEstPasCaseTrouvee();}
    public boolean contientCandidatUnique(int numCase) {return this.getCase(numCase).contientCandidatUnique();}
    public boolean isCandidat(int numCase, int candidat) {return this.getCase(numCase).isCandidat(candidat);}
    public boolean isCandidat(int x, int y, int candidat) {return this.getCase(x,y).isCandidat(candidat);}
    public int getValeurCase(int numCase) {return this.getCase(numCase).getValeur();}
    public int getNombreCandidats(int numCase) {return this.getCase(numCase).getNombreCandidats();}
    public int getxCase(int numCase) { return this.getCase(numCase).getxCase();}
    public int getyCase(int numCase) { return this.getCase(numCase).getyCase();}
    public int getRegion(int numCase) {return this.getCase(numCase).getRegion();}
    public boolean [] getCandidatsTabBoolean(int numCase) {return this.getCase(numCase).getCandidatsTabBoolean();}
    public boolean [] getCandidatsTabBoolean(int x, int y) {return this.getCase(x,y).getCandidatsTabBoolean();}
    public int calculValeurUnique(int numCase) {return this.getCase(numCase).calculValeurUnique();}
    public String construitLibelleCandidats(int numCase) {return this.getCase(numCase).construitLibelleCandidats();}

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (int i=1;i<81;i++) {
    		sb.append(i);
    		sb.append("  : ");
    		sb.append(this.getCase(i).isCaseInitiale());
    		sb.append(this.getCase(i).isCaseATrouver());
    		sb.append(this.getCase(i).isCaseTrouvee());
    		sb.append(" ");
    		sb.append(this.getCase(i).getCandidats().getNombreCandidats());
    		sb.append(" ==> ");
    		sb.append(this.getCase(i).getCandidats().toString());
            sb.append("=");
            sb.append(this.getCase(i).getValeur());
    		if (i % 9 == 0) {sb.append("\n");} else {sb.append(" | ");}
    	}
    	return sb.toString();	
    }
    
    public void setValeurCaseEnCours(int solution) {
        this.getCaseEnCours().setCaseTrouvee(solution);
        this.elimineCandidatsCaseTrouvee(CaseEnCours.getX(), CaseEnCours.getY(), solution);
        casesAtrouver.remove(casesAtrouver.indexOf(calculNumCase(CaseEnCours.getX(), CaseEnCours.getY())));
    }        
    
    public boolean checkPresenceCandidatLigne(int valeur, int x, int numLigne) {
        for (int i=0;i<9;i++) {
            if (mesCases[i][numLigne].nEstPasCaseInitiale() && 
                mesCases[i][numLigne].nEstPasCaseTrouvee() &&
                i!=x && this.mesCases[i][numLigne].isCandidat(valeur)) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceCandidatColonne(int valeur, int numcol, int y) {
        for (int i=0;i<9;i++) {
            if (mesCases[numcol][i].nEstPasCaseInitiale() && 
                mesCases[numcol][i].nEstPasCaseTrouvee() &&
                i!=y && this.mesCases[numcol][i].isCandidat(valeur)) {return true;}
        }
        return false;
    }

    public boolean checkPresenceCandidatRegion(int indiceCandidat, int x, int y) { 
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (mesCases[abs][ord].nEstPasCaseInitiale() && 
                    mesCases[abs][ord].nEstPasCaseTrouvee() &&
                    (x!=abs || y!=ord) && 
                    this.mesCases[abs][ord].isCandidat(indiceCandidat)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void elimineCandidatsCaseTrouvee(int x, int y, int solution) {
        //Case x, y trouvée ==> élimination du candidats dans ligne/colonne/région.
        //Elimination dans ligne : 
        for (int i=0;i<9;i++) {
            mesCases[i][y].elimineCandidat(solution);
        }
        
        //Elimination dans colonne : 
        for (int i=0;i<9;i++) {
            mesCases[x][i].elimineCandidat(solution);
        }
        
        //Elimination dans région : 
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                mesCases[abs][ord].elimineCandidat(solution);
            }
        }
    }

    void calculTousLesCandidats() {
        for (Integer caseATrouver : this.getCasesAtrouver()) {
            logger.debug("Avant : {} {}", caseATrouver, this.getCase(caseATrouver));
            CaseEnCours.setCaseEnCours(caseATrouver);
            this.calculCandidatsInitiaux(CaseEnCours.getX(), CaseEnCours.getY());
            logger.debug("Après : {} {}", caseATrouver, this.getCase(caseATrouver));
        }
    }

    private void calculCandidatsInitiaux(int x, int y) {
        for (int valeur=1;valeur<10;valeur++) {
            if (this.checkPresenceValeurLigne(valeur, y))
            {this.getCase(x, y).elimineCandidat(valeur);}
            if (this.checkPresenceValeurColonne(valeur, x))
            {this.getCase(x, y).elimineCandidat(valeur);}
            if (this.checkPresenceValeurRegion(valeur))
            {this.getCase(x, y).elimineCandidat(valeur);}
        }
    }

    boolean checkPresenceValeurLigne(int valeur, int numLigne) {
        for (int i=0;i<9;i++) {
            if (this.getCase(i, numLigne).getValeur()==valeur) {return true;}
        }
        return false;
    }

    boolean checkPresenceValeurColonne(int valeur, int numColonne) {
        for (int i=0;i<9;i++) {
            if (this.getCase(numColonne, i).getValeur()==valeur) {return true;}
        }
        return false;
    }

    boolean checkPresenceValeurRegion(int valeur) {
        for (int x=CaseEnCours.getxRegion();x<CaseEnCours.getxRegion()+3;x++) {
            for (int y=CaseEnCours.getyRegion();y<CaseEnCours.getyRegion()+3;y++) {
                if (this.getCase(x, y).getValeur() == valeur) {return true;}
            }
        }
        return false;
    }

    public static int calculNumCase(int x, int y) {
        return (y*9+x+1);
    }

    public int calculNombreCaseATrouverDansLigne(int ySearch) {
        int resultat = 0;
        for (int i=0;i<9;i++) {
            if (this.nEstPasCaseInitiale(calculNumCase(i,ySearch)) && this.nEstPasCaseTrouvee(calculNumCase(i,ySearch)))
                resultat+=1;
        }
        return resultat;
    }

    public int calculNombreCaseATrouverDansColonne(int xSearch) {
        int resultat = 0;
        for (int i=0;i<9;i++) {
            if (this.nEstPasCaseInitiale(calculNumCase(xSearch,i)) && this.nEstPasCaseTrouvee(calculNumCase(xSearch,i)))
                resultat+=1;
        }
        return resultat;
    }
}
