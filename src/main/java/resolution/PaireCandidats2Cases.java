package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.ArrayList;
import java.util.Optional;

public abstract class PaireCandidats2Cases extends MethodeResolution {
	protected int nb2inter = 0;
	protected int nb1inter = 0;

	protected int candidatAEliminer;
	
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
		this.inserePaireCandidatsDansTab(context);

		//Boucle sur chaque couple possible de la case en cours : 
		for (CandidatsCase couple : tabCandidats) {
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
		return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context));
	}

	private void razCompteursIntersections() {
		nb1inter=0;
		nb2inter=0;
	}
	
	public void inserePaireCandidatsDansTab(CaseContext context) {
		ArrayList<Integer> tabTemp = new ArrayList<>();
		for (int i=1;i<10;i++) {
			if (grille.isCandidat(context.getNumCase(), i)) tabTemp.add(i);
		}
		
		int i = 0;
		int j;

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
	
	protected abstract boolean traiteCouple(CaseContext context, CandidatsCase paireCandidats);

	public void calculIntersectionDeuxCases(CandidatsCase c1, CandidatsCase c2) {
		boolean[] cInter = Utils.calculEtLogique2Candidats(c1.getCandidats(), c2.getCandidats());
		int nbInter = Utils.calculNombreCandidats(cInter);
		if (nbInter ==1) {nb1inter+=1;}
		if (nbInter ==2) {nb2inter+=1;}
	}
	
}
