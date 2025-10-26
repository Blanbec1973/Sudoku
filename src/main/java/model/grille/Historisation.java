package model.grille;

import java.util.ArrayList;
import java.util.List;

public class Historisation {
	private final List<Grille> histoGrille = new ArrayList<>();
	
	public Grille getHistoGrille(int i) {return histoGrille.get(i);}
	public List<Grille> getHistoGrille() {return histoGrille;}
	
	public void historiseGrille(Grille grille) {
		histoGrille.add(new Grille());
		this.copyGrille(grille, histoGrille.get(histoGrille.size()-1));
	}
	
	public void supprimeDerniereGrille(Grille grille) {
		histoGrille.remove(histoGrille.size()-1);
		this.copyGrille(histoGrille.get(histoGrille.size()-1), grille);
	}
	
	private void copyGrille(Grille source, Grille cible) {
		// grille : tableau de Cases + liste casesATrouver
		//     Case : numCase, xCase, yCase, valeur, region, etatCase, candidats
		//			Candidats : candidats, nombreCandidats
		
		cible.getCasesAtrouver().clear();
		for (int numCaseATrouver : source.getCasesAtrouver()) {
			cible.getCasesAtrouver().add(numCaseATrouver);
		}
		
		for (int i=1;i<82;i++) {		
			cible.getCase(i).setCaseTrouvee(source.getValeurCase(i));
			cible.getCase(i).setEtatCase(source.getCase(i).getEtatCase());
			this.copyCandidats(source.getCase(i).getCandidats(), cible.getCase(i).getCandidats());				                                                          
		}
	}

	private void copyCandidats(CandidatsCase input, CandidatsCase output) {
		boolean[] candidatsInput = input.getCandidats();
		output.setCandidats(candidatsInput.clone());
	}

    public void reloadGrille(Grille grille) {
		histoGrille.clear();
		historiseGrille(grille);
    }
}
