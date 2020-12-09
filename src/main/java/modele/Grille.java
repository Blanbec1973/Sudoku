package modele;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private Case [][] mesCases = new Case [9][9];
    private Modele modele;
    private List<Integer> casesAtrouver;
        
    public Grille(Modele modele) {
        this.modele = modele;
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
    public Case getCaseEnCours() {return mesCases[CaseEnCours.getXSearch()][CaseEnCours.getYSearch()];}
    public List<Integer> getCasesAtrouver() {return casesAtrouver;}
    
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
            if (mesCases[i][y].nEstPasCaseInitiale() && mesCases[i][y].nEstPasCaseTrouvee()) {
            	modele.getControle().demandeRefreshAffichageCase(i, y);
            }
        }
        
        //Elimination dans colonne : 
        for (int i=0;i<9;i++) {
            mesCases[x][i].elimineCandidat(solution);
            if (mesCases[x][i].nEstPasCaseInitiale() && mesCases[x][i].nEstPasCaseTrouvee()) {
            	modele.getControle().demandeRefreshAffichageCase(x, i);
            }
        }
        
        //Elimination dans région : 
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                mesCases[abs][ord].elimineCandidat(solution);
                if (mesCases[abs][ord].nEstPasCaseInitiale() && mesCases[abs][ord].nEstPasCaseTrouvee()) {
                	modele.getControle().demandeRefreshAffichageCase(abs, ord);
                }
            }
        }
    }
}
