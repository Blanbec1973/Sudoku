package model.service;

import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.List;

public class GrilleService {

    private final Grille grille;
    private final GrilleAnalysisService grilleAnalysisService;

    public GrilleService(Grille grille) {
        this.grille = grille;
        grilleAnalysisService = new GrilleAnalysisService(grille);
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
    // Methodes d'analyse
    public boolean checkPresenceValeurLigne(int valeur, int numLigne) {
        return grilleAnalysisService.checkPresenceValeurLigne(valeur, numLigne);
    }
    public boolean checkPresenceValeurColonne(int valeur, int numColonne) {
        return grilleAnalysisService.checkPresenceValeurColonne(valeur, numColonne);
    }
    public boolean checkPresenceValeurRegion(int valeur) {
        return grilleAnalysisService.checkPresenceValeurRegion(valeur);
    }
    public boolean checkPresenceCandidatLigne(int valeur, int x, int numLigne) {
        return grilleAnalysisService.checkPresenceCandidatLigne(valeur,x, numLigne);
    }
    public boolean checkPresenceCandidatColonne(int valeur, int numcol, int y) {
        return grilleAnalysisService.checkPresenceCandidatColonne(valeur, numcol, y);
    }
    public boolean checkPresenceCandidatRegion(int indiceCandidat, int x, int y) {
        return grilleAnalysisService.checkPresenceCandidatRegion(indiceCandidat, x, y);
    }
    public int calculNombreCaseATrouverDansLigne(int ySearch) {
        return grilleAnalysisService.calculNombreCaseATrouverDansLigne(ySearch);
    }
    public int calculNombreCaseATrouverDansColonne(int xSearch) {
        return grilleAnalysisService.calculNombreCaseATrouverDansColonne(xSearch);
    }

}