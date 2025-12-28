package model.grille;

import java.util.ArrayList;
import java.util.List;

public class Historisation {
	private final List<Grille> histoGrille = new ArrayList<>();
	
	public Grille getHistoGrille(int i) {return histoGrille.get(i);}
	public List<Grille> getHistoGrille() {return histoGrille;}
	
	public void historiseGrille(Grille grille) {
		histoGrille.add(new Grille());
		CopyGrilleService.copyGrille(grille, histoGrille.get(histoGrille.size()-1));
	}
	
	public void supprimeDerniereGrille(Grille grille) {
		histoGrille.remove(histoGrille.size()-1);
        CopyGrilleService.copyGrille(histoGrille.get(histoGrille.size()-1), grille);
	}

    public void reloadGrille(Grille grille) {
		histoGrille.clear();
		historiseGrille(grille);
    }
}
