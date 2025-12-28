package model.service;

import model.grille.CaseContext;
import model.grille.Grille;
import model.grille.GrilleUtils;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class GrilleAnalysisService {
    private final Grille grille;

    GrilleAnalysisService(Grille grille) {
        this.grille = grille;
    }

    private boolean checkPresence(IntPredicate condition) {
        return IntStream.range(0, 9).anyMatch(condition);
    }

    public boolean checkPresenceValeurLigne(CaseContext context, int valeur) {
        int numLigne = context.getY();
        return checkPresence(i-> GrilleUtils.getValeurCase(grille, i, numLigne) == valeur);
    }

    public boolean checkPresenceValeurColonne(CaseContext context, int valeur) {
        int numColonne = context.getX();
        return checkPresence(i-> GrilleUtils.getValeurCase(grille, numColonne, i) == valeur);
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

    private int calculNombreCaseATrouver(IntPredicate condition) {
        return (int) IntStream.range(0, 9)
                .filter(condition)
                .count();
    }

    public int calculNombreCaseATrouverDansLigne(int ySearch) {
        return calculNombreCaseATrouver(i->grille.nEstPasCaseInitiale(i, ySearch)
                        && grille.nEstPasCaseTrouvee(i, ySearch));
    }

    public int calculNombreCaseATrouverDansColonne(int xSearch) {
        return calculNombreCaseATrouver(i-> grille.nEstPasCaseInitiale(xSearch,i)
                        && grille.nEstPasCaseTrouvee(xSearch,i));
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
