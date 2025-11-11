package model.service;

import model.grille.CaseContext;
import model.grille.Grille;
import resolution.ResolutionAction;

public class GrilleService {
    private final GrilleAnalysisService grilleAnalysisService;
    private final GrilleUpdateService grilleUpdateService;

    public GrilleService(Grille grille) {
        grilleAnalysisService = new GrilleAnalysisService(grille);
        grilleUpdateService = new GrilleUpdateService(grille, grilleAnalysisService);
    }

    //Méthodes d'update :
    public void elimineCandidatsCaseTrouvee(ResolutionAction action) {
        grilleUpdateService.elimineCandidatsCaseTrouvee(action);
    }
    public void calculTousLesCandidats() {
        grilleUpdateService.calculTousLesCandidats();
    }
    public void calculCandidatsInitiaux(int x, int y) {
        grilleUpdateService.calculCandidatsInitiaux(new CaseContext(x, y));
    }
    // Methodes d'analyse
    public boolean checkPresenceValeurLigne(CaseContext context, int valeur) {
        return grilleAnalysisService.checkPresenceValeurLigne(context, valeur);
    }
    public boolean checkPresenceValeurColonne(CaseContext context, int valeur) {
        return grilleAnalysisService.checkPresenceValeurColonne(context, valeur);
    }
    public boolean checkPresenceValeurRegion(CaseContext context, int valeur) {
        return grilleAnalysisService.checkPresenceValeurRegion(context, valeur);
    }
    public boolean checkPresenceCandidatLigne(CaseContext context, int valeur) {
        return grilleAnalysisService.checkPresenceCandidatLigne(context, valeur);
    }
    public boolean checkPresenceCandidatColonne(CaseContext context, int valeur) {
        return grilleAnalysisService.checkPresenceCandidatColonne(context, valeur);
    }
    public boolean checkPresenceCandidatRegion(CaseContext context, int indiceCandidat) {
        return grilleAnalysisService.checkPresenceCandidatRegion(context, indiceCandidat);
    }
    public int calculNombreCaseATrouverDansLigne(int ySearch) {
        return grilleAnalysisService.calculNombreCaseATrouverDansLigne(ySearch);
    }
    public int calculNombreCaseATrouverDansColonne(int xSearch) {
        return grilleAnalysisService.calculNombreCaseATrouverDansColonne(xSearch);
    }

}