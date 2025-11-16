package model.service;

import model.grille.CaseContext;
import model.grille.Grille;

public class GrilleAnalysisService {
    private final Grille grille;

    GrilleAnalysisService(Grille grille) {
        this.grille = grille;
    }

    public boolean checkPresenceValeurLigne(CaseContext context, int valeur) {
        int numLigne = context.getY();
        for (int i = 0; i < 9; i++) {
            if (grille.getValeurCase(i, numLigne) == valeur) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPresenceValeurColonne(CaseContext context, int valeur) {
        int numColonne = context.getX();
        for (int i = 0; i < 9; i++) {
            if (grille.getValeurCase(numColonne, i) == valeur) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPresenceValeurRegion(CaseContext context, int valeur) {
        int xStart = context.getxRegion();
        int yStart = context.getyRegion();
        for (int x = xStart; x < xStart + 3; x++) {
            for (int y = yStart; y < yStart + 3; y++) {
                if (grille.getValeurCase(x, y) == valeur) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkPresenceCandidatLigne(CaseContext context, int valeur) {
        int numLigne = context.getY();
        int x = context.getX();
        for (int i=0;i<9;i++) {
            if (grille.nEstPasCaseInitiale(i, numLigne) &&
                    grille.nEstPasCaseTrouvee(i, numLigne) &&
                    i!=x && grille.isCandidat(i, numLigne, valeur)) {return true;}
        }
        return false;
    }
    public boolean checkPresenceCandidatColonne(CaseContext context, int valeur) {
        int numCol = context.getX();
        int y = context.getY();
        for (int i=0;i<9;i++) {
            if (grille.nEstPasCaseInitiale(numCol, i) &&
                    grille.nEstPasCaseTrouvee(numCol, i) &&
                    i!=y && grille.isCandidat(numCol, i, valeur)) {return true;}
        }
        return false;
    }
    public boolean checkPresenceCandidatRegion(CaseContext context, int indiceCandidat) {
        int x = context.getX();
        int y = context.getY();
        for (int abs = context.getxRegion(); abs<context.getxRegion()+3; abs++) {
            for (int ord=context.getyRegion();ord<context.getyRegion()+3;ord++) {
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

    public Integer calculNombreCaseATrouverDansBloc(CaseContext context) {
        int resultat = 0;
        int xRegion = context.getxRegion();
        int yRegion = context.getyRegion();
        for (int abs = xRegion; abs < xRegion + 3; abs++) {
            for (int ord = yRegion; ord < yRegion + 3; ord++) {
                if (grille.nEstPasCaseInitiale(abs,ord) && grille.nEstPasCaseTrouvee(abs,ord))
                    resultat+=1;
            }
        }
        return resultat;
    }
}
