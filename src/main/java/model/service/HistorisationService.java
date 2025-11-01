package model.service;

import model.grille.Grille;
import model.grille.Historisation;

public class HistorisationService {

    private final Historisation historisation = new Historisation();

    public void historiseGrille(Grille grille) {
        historisation.historiseGrille(grille);
    }

    public void reloadGrille(Grille grille) {
        historisation.reloadGrille(grille);
    }

    public void supprimeDerniereGrille(Grille grille) {
        historisation.supprimeDerniereGrille(grille);
    }

    public boolean canReloadLastHistoricization() {
        return historisation.getHistoGrille().size() > 1;
    }
}