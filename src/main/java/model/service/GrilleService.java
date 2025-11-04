package model.service;

import model.grille.CaseContext;
import model.grille.Grille;

public class GrilleService {
    private final GrilleAnalysisService grilleAnalysisService;
    private final GrilleUpdateService grilleUpdateService;

    public GrilleService(Grille grille) {
        grilleAnalysisService = new GrilleAnalysisService(grille);
        grilleUpdateService = new GrilleUpdateService(grille, grilleAnalysisService);
    }

    //Méthodes d'update :
    public void elimineCandidatsCaseTrouvee(int x, int y, int solution) {
        grilleUpdateService.elimineCandidatsCaseTrouvee(x, y, solution);
    }
    public void calculTousLesCandidats() {
        grilleUpdateService.calculTousLesCandidats();
    }
    public void calculCandidatsInitiaux(int x, int y) {
        grilleUpdateService.calculCandidatsInitiaux(new CaseContext(x, y));
    }
    // Methodes d'analyse
    public boolean checkPresenceValeurLigne(int valeur, int numLigne) {
        return grilleAnalysisService.checkPresenceValeurLigne(valeur, numLigne);
    }
    public boolean checkPresenceValeurColonne(int valeur, int numColonne) {
        return grilleAnalysisService.checkPresenceValeurColonne(valeur, numColonne);
    }
    public boolean checkPresenceValeurRegion(CaseContext context, int valeur) {
        return grilleAnalysisService.checkPresenceValeurRegion(context, valeur);
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