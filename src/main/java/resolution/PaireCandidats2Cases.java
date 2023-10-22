package resolution;

import java.util.ArrayList;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import utils.Utils;

public abstract class PaireCandidats2Cases extends MethodeResolution {
	protected int nb2inter = 0;
	protected int nb1inter = 0;
	
	protected PaireCandidats2Cases(Model model, Grille grille) {
		super(model,grille);
		c1=0;
		c2=0;
		caseTrouvee=false;
	}
	
	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		boolean trouve = false;
		candidatAEliminer=0;
		
		if (grille.getCaseEnCours().getNombreCandidats()<3) return false;
		
		// Recherche des paires de candidats dans la case en cours, mise en tableau :
		this.inserePaireCandidatsDansTab();
		
		//Boucle sur chaque couple possible de la case en cours : 
		for (CandidatsCase couple : tabCandidats) {
			razCompteursIntersections();
			if (this.traiteCouple(couple)) {
				trouve = true;
				break;
			}
		}
			
		if (!trouve) return false;
		if (grille.getCaseEnCours().getNombreCandidats() == 2) return false;
		
		//Recherche du premier candidat à éliminer : 
		for (candidatAEliminer=1;candidatAEliminer<10;candidatAEliminer++) {
			if (grille.getCaseEnCours().isCandidat(candidatAEliminer) && candidatAEliminer !=c1 && candidatAEliminer != c2) {
				break;
			}
		}
		
		numCaseAction = CaseEnCours.getNumCase();
		return true;
	}

	private void razCompteursIntersections() {
		nb1inter=0;
		nb2inter=0;
	}
	
	public void inserePaireCandidatsDansTab() {
		ArrayList<Integer> tabTemp = new ArrayList<>();
		for (int i=1;i<10;i++) {
			if (grille.getCaseEnCours().isCandidat(i)) tabTemp.add(i);
		}
		
		int i = 0;
		int j = 0;

		while (i<tabTemp.size()) {
			j = i+1;
			while (j<tabTemp.size()) {
				tabCandidats.add(new CandidatsCase());
				tabCandidats.get(tabCandidats.size()-1).setAllCandidatsToFalse();
				tabCandidats.get(tabCandidats.size()-1).setCandidat(tabTemp.get(i));
				tabCandidats.get(tabCandidats.size()-1).setCandidat(tabTemp.get(j));				
				j+=1;
			}
			i+=1;	
		}
	}
	
	protected abstract boolean traiteCouple(CandidatsCase paireCandidats);

	public void calculIntersectionDeuxCases(CandidatsCase c1, CandidatsCase c2) {
		boolean[] cInter = Utils.calculEtLogique2Candidats(c1.getCandidats(), c2.getCandidats());
		int nbInter = Utils.calculNombreCandidats(cInter);
		if (nbInter ==1) {nb1inter+=1;}
		if (nbInter ==2) {nb2inter+=1;}
	}
	
}
