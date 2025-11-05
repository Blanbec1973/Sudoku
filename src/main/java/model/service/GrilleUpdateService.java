package model.service;

import model.grille.CaseContext;
import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.List;

public class GrilleUpdateService {
    private final Grille grille;
    private final GrilleAnalysisService grilleAnalysisService;

    public GrilleUpdateService(Grille grille, GrilleAnalysisService grilleAnalysisService) {
        this.grille = grille;
        this.grilleAnalysisService=grilleAnalysisService;
    }

    void elimineCandidatsCaseTrouvee(int x, int y, int solution) {
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
    void calculTousLesCandidats() {
        List<Integer> casesATrouver = grille.getCasesAtrouver();
        for (Integer numCase : casesATrouver) {
            calculCandidatsInitiaux(new CaseContext(numCase));
        }
    }
    void calculCandidatsInitiaux(CaseContext context) {
        for (int valeur = 1; valeur <= 9; valeur++) {
            if (grilleAnalysisService.checkPresenceValeurLigne(context, valeur)) {
                grille.elimineCandidat(context.getX(), context.getY(), valeur);
            }
            if (grilleAnalysisService.checkPresenceValeurColonne(context, valeur)) {
                grille.elimineCandidat(context.getX(), context.getY(), valeur);
            }
            if (grilleAnalysisService.checkPresenceValeurRegion(context, valeur)) {
                grille.elimineCandidat(context.getX(), context.getY(), valeur);
            }
        }
    }

}
