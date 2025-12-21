package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import model.grille.CandidatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class PaireCandidats2Cases extends MethodeResolution {
	protected int nb2inter = 0;
	protected int nb1inter = 0;

	protected int candidatAEliminer;
	protected int c1;
	protected  int c2;
	
	protected PaireCandidats2Cases(Grille grille) {
		super(grille);
		c1=0;
		c2=0;
	}
	
	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		boolean trouve = false;
		candidatAEliminer=0;

		//On ne traite la case en cours que si elle a plus de 2 candidats :
		if (grille.getNombreCandidats(context.getNumCase())<3) return Optional.empty();
		
		// Recherche des paires de candidats dans la case en cours, mise en tableau :
		List<CandidatsCase> tabCandidatsLocal = inserePaireCandidatsDansTab(context);

		//Boucle sur chaque couple possible de la case en cours :
		for (CandidatsCase couple : tabCandidatsLocal) {
			razCompteursIntersections();
			if (this.traiteCouple(context, couple)) {
				trouve = true;
				break;
			}
		}


		if (!trouve) return Optional.empty();
		if (grille.getNombreCandidats(context.getNumCase()) == 2) return Optional.empty();
		
		//Recherche du premier candidat à éliminer : 
		for (candidatAEliminer=1;candidatAEliminer<10;candidatAEliminer++) {
			if (grille.isCandidat(context.getNumCase(), candidatAEliminer) && candidatAEliminer !=c1 && candidatAEliminer != c2) {
				break;
			}
		}

		int numCaseAction = context.getNumCase();
		int[] candidatsUtilises = {
				c1,
				c2
		};
		return Optional.of(new ResolutionAction(numCaseAction, null,
				candidatAEliminer, this, context, candidatsUtilises));
	}

	private void razCompteursIntersections() {
		nb1inter=0;
		nb2inter=0;
	}
	

		//sert à générer toutes les combinaisons possibles de paires de candidats présents dans une case,
		// puis à les stocker dans tabCandidats sous forme d’objets CandidatsCase.
		List<CandidatsCase> inserePaireCandidatsDansTab(CaseContext context) {
			List<CandidatsCase> tabCandidatsLocal = new ArrayList<>();
			List<Integer> tabTemp = new ArrayList<>();
			for (int i = 1; i < 10; i++) {
				if (grille.isCandidat(context.getNumCase(), i)) tabTemp.add(i);
			}

			for (int i = 0; i < tabTemp.size(); i++) {
				for (int j = i + 1; j < tabTemp.size(); j++) {
					CandidatsCase paire = new CandidatsCase();
					paire.setAllCandidatsToFalse();
					paire.setCandidat(tabTemp.get(i));
					paire.setCandidat(tabTemp.get(j));
					tabCandidatsLocal.add(paire);
				}
			}
			return tabCandidatsLocal;
		}
	
	protected abstract boolean traiteCouple(CaseContext context, CandidatsCase paireCandidats);

	public void calculIntersectionDeuxCases(CandidatsCase c1, CandidatsCase c2) {
		CandidatsCase cInter = CandidatUtils.calculEtLogique2Candidats(c1, c2);
		int nbInter = cInter.getNombreCandidats();
		if (nbInter ==1) {nb1inter+=1;}
		if (nbInter ==2) {nb2inter+=1;}
	}
	
}
