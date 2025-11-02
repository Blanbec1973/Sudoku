package model.service;

import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.List;

public class GrilleService {

    private final Grille grille;

    public GrilleService(Grille grille) {
        this.grille = grille;
    }

    public boolean checkPresenceValeurLigne(int valeur, int numLigne) {
        for (int i = 0; i < 9; i++) {
            if (grille.getValeurCase(i, numLigne) == valeur) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPresenceValeurColonne(int valeur, int numColonne) {
        for (int i = 0; i < 9; i++) {
            if (grille.getValeurCase(numColonne, i) == valeur) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPresenceValeurRegion(int valeur) {
        int xStart = CaseEnCours.getxRegion();
        int yStart = CaseEnCours.getyRegion();
        for (int x = xStart; x < xStart + 3; x++) {
            for (int y = yStart; y < yStart + 3; y++) {
                if (grille.getValeurCase(x, y) == valeur) {
                    return true;
                }
            }
        }
        return false;
    }

    public void elimineCandidatsCaseTrouvee(int x, int y, int solution) {
        // Ligne
        for (int i = 0; i < 9; i++) {
            grille.elimineCandidat(i, y, solution);
        }
        // Colonne
        for (int i = 0; i < 9; i++) {
            grille.elimineCandidat(x, i, solution);
        }
        // Région
        int xStart = CaseEnCours.getxRegion();
        int yStart = CaseEnCours.getyRegion();
        for (int abs = xStart; abs < xStart + 3; abs++) {
            for (int ord = yStart; ord < yStart + 3; ord++) {
                grille.elimineCandidat(abs, ord, solution);
            }
        }
    }

    public void calculTousLesCandidats() {
        List<Integer> casesATrouver = grille.getCasesAtrouver();
        for (Integer numCase : casesATrouver) {
            CaseEnCours.setCaseEnCours(numCase);
            calculCandidatsInitiaux(CaseEnCours.getX(), CaseEnCours.getY());
        }
    }

    private void calculCandidatsInitiaux(int x, int y) {
        for (int valeur = 1; valeur <= 9; valeur++) {
            if (checkPresenceValeurLigne(valeur, y)) {
                grille.elimineCandidat(x, y, valeur);
            }
            if (checkPresenceValeurColonne(valeur, x)) {
                grille.elimineCandidat(x, y, valeur);
            }
            if (checkPresenceValeurRegion(valeur)) {
                grille.elimineCandidat(x, y, valeur);
            }
        }
    }
    public boolean checkPresenceCandidatLigne(int valeur, int x, int numLigne) {
        for (int i=0;i<9;i++) {
            if (grille.nEstPasCaseInitiale(i, numLigne) &&
                    grille.nEstPasCaseTrouvee(i, numLigne) &&
                    i!=x && grille.isCandidat(i, numLigne, valeur)) {return true;}
        }
        return false;
    }
    public boolean checkPresenceCandidatColonne(int valeur, int numcol, int y) {
        for (int i=0;i<9;i++) {
            if (grille.nEstPasCaseInitiale(numcol, i) &&
                    grille.nEstPasCaseTrouvee(numcol, i) &&
                    i!=y && grille.isCandidat(numcol, i, valeur)) {return true;}
        }
        return false;
    }
    public boolean checkPresenceCandidatRegion(int indiceCandidat, int x, int y) {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (grille.nEstPasCaseInitiale(abs, ord) &&
                        grille.nEstPasCaseTrouvee(abs, ord) &&
                        (x!=abs || y!=ord) &&
                        grille.isCandidat(abs, ord, indiceCandidat)) {
                    return true;
                }
            }
        }
        return false;
    }
    public int calculNombreCaseATrouverDansLigne(int ySearch) {
        int resultat = 0;
        for (int i=0;i<9;i++) {
            if (grille.nEstPasCaseInitiale(i,ySearch) && grille.nEstPasCaseTrouvee(i,ySearch))
                resultat+=1;
        }
        return resultat;
    }

    public int calculNombreCaseATrouverDansColonne(int xSearch) {
        int resultat = 0;
        for (int i=0;i<9;i++) {
            if (grille.nEstPasCaseInitiale(xSearch,i) && grille.nEstPasCaseTrouvee(xSearch,i))
                resultat+=1;
        }
        return resultat;
    }

}