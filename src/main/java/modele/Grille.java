package modele;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private Case [][] mesCases = new Case [9][9];
    private List<Integer> casesAtrouver;
        
    public Grille() {
        int numcase = 0;
        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                numcase = numcase +1;
                mesCases[x][y] = new Case(numcase, x, y);
            }
        }
        casesAtrouver = new ArrayList<>();
    }
     
    public Case getCase(int x, int y) {return mesCases[x][y];}
    public Case getCase(int numCase) {return mesCases[Utils.calculXsearch(numCase)][Utils.calculYsearch(numCase)];}
    public Case getCaseEnCours() {return mesCases[CaseEnCours.getXSearch()][CaseEnCours.getYSearch()];}
    public List<Integer> getCasesAtrouver() {return casesAtrouver;}
    
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
    		sb.append(this.getCase(i).getCandidats().displayCandidats());
    		sb.append(" | ");
    	}
    	return sb.toString();	
    }
    
    public void displayGrille() {
    	System.out.println(this.toString());
    }
    
    public void setValeurCaseEnCours(int solution) {
        this.getCaseEnCours().setValeurCase(solution);
        this.elimineCandidatsCaseTrouvee(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), solution);
        casesAtrouver.remove(casesAtrouver.indexOf(Utils.calculNumCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch())));
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
}
