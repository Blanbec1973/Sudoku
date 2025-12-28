package model.service;

import model.grille.CaseContext;
import model.grille.Grille;
import model.grille.GrilleUtils;

import java.util.stream.IntStream;

public class GrilleAnalysisService {
    private final Grille grille;

    GrilleAnalysisService(Grille grille) {
        this.grille = grille;
    }

    public boolean checkPresenceValeurLigne(CaseContext context, int valeur) {
        int numLigne = context.getY();
        for (int i = 0; i < 9; i++) {
            if (GrilleUtils.getValeurCase(grille, i, numLigne) == valeur) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPresenceValeurColonne(CaseContext context, int valeur) {
        int numColonne = context.getX();
        for (int i = 0; i < 9; i++) {
            if (GrilleUtils.getValeurCase(grille, numColonne, i) == valeur) {
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
                if (GrilleUtils.getValeurCase(grille, x, y) == valeur) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkPresenceCandidatLigne(CaseContext context, int valeur) {
        int numLigne = context.getY();
        int x = context.getX();

        return IntStream.range(0,9).filter(i->grille.nEstPasCaseInitiale(i, numLigne))
                .filter(i->grille.nEstPasCaseTrouvee(i, numLigne))
                .filter(i-> i!=x)
                .anyMatch(i-> grille.isCandidat(i, numLigne, valeur));
    }
    public boolean checkPresenceCandidatColonne(CaseContext context, int valeur) {
        int numCol = context.getX();
        int y = context.getY();
        return IntStream.range(0,9).filter(i->grille.nEstPasCaseInitiale(numCol, i))
                .filter(i->grille.nEstPasCaseTrouvee(numCol, i))
                .filter(i-> i!=y)
                .anyMatch(i-> grille.isCandidat(numCol,i, valeur));
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
        return (int) IntStream.range(0, 9)
                .filter(i -> grille.nEstPasCaseInitiale(i, ySearch)
                        && grille.nEstPasCaseTrouvee(i, ySearch))
                .count();
    }

    public int calculNombreCaseATrouverDansColonne(int xSearch) {
        return (int) IntStream.range(0, 9)
                .filter(i-> grille.nEstPasCaseInitiale(xSearch,i)
                        && grille.nEstPasCaseTrouvee(xSearch,i))
                .count();
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
