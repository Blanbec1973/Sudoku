package resolution;

import java.util.ArrayList;

import modele.Candidats;
import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class PaireCandidats2CasesColonne extends MethodeResolution {

	public PaireCandidats2CasesColonne(Modele modele, Grille grille) {
		super(modele,grille);
		c1=0;
		c2=0;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		boolean trouve = false;
		int candiatEliminable=0;
		
		if (CaseEnCours.getNumCase() ==20) 
			System.out.println("Stop");
		
		if (grille.getCaseEnCours().getNombreCandidats()<3) return false;
		
		// Recherche des paires de candidats dans la case en cours, mise en tableau :
		this.inserePaireCandidatsDansTab();
		
		//Boucle sur chaque couple possible de la case en cours : 
		for (Candidats couple : tabCandidats) {
			if (this.traiteCouple(couple)) {
				trouve = true;
				break;
			}
		}
			
		if (!trouve) return false;
		if (grille.getCaseEnCours().getNombreCandidats() == 2) return false;
		
		//Recherche du premier candidat à éliminer : 
		for (int i=1;i<10;i++) {
			if (grille.getCaseEnCours().isCandidat(i) && i !=c1 && i != c2) {
				candiatEliminable=i;
				break;
			}
		}
		
		if (goPourChangement) {
			this.elimineCandidatCaseEnCours(candiatEliminable);
			return true;
		}
		else {
			modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
				                                      CaseEnCours.getYSearch(),
                                                      grille.getCaseEnCours().construitLibelleCandidats());
			return true;
		}
			
	}
	
	public void inserePaireCandidatsDansTab() {
		ArrayList<Integer> tabTemp = new ArrayList<Integer>();
		for (int i=1;i<10;i++) {
			if (grille.getCaseEnCours().isCandidat(i)) tabTemp.add(i);
		}
		
		//for (int i : tabTemp) {System.out.println(String.valueOf(i));}
		
		int i = 0;
		int j = 0;

		while (i<tabTemp.size()) {
			j = i+1;
			while (j<tabTemp.size()) {
				tabCandidats.add(new Candidats());
				tabCandidats.get(tabCandidats.size()-1).setAllCandidatsToFalse();
				tabCandidats.get(tabCandidats.size()-1).setCandidat(tabTemp.get(i));
				tabCandidats.get(tabCandidats.size()-1).setCandidat(tabTemp.get(j));				
				j+=1;
			}
			i+=1;
			
		}
		
		//for (Candidats c : tabCandidats) {System.out.println(c.displayCandidats());}	
	}
	
	private boolean traiteCouple(Candidats paireCandidats) {
		int nb2inter = 0;
		int nb1inter = 0;
		
		for (int i=0;i<9;i++) {
			if (grille.getCase(CaseEnCours.getXSearch(),i).isCaseATrouver() &&
				CaseEnCours.getYSearch()!=i) {
				
				switch (Utils.calculNombreCandidats(
						Utils.calculEtLogique2Candidats(paireCandidats.getCandidats(), 
								grille.getCase(CaseEnCours.getXSearch(), i).getCandidatsTabBoolean()))) {
					case 1 -> {nb1inter+=1;}
					case 2 -> {nb2inter+=1;}
				}				
			}
		}
		
		if (nb2inter == 1 && nb1inter == 0) {
			c1 = Utils.trouveCandidatNumero(paireCandidats, 1);
			c2 = Utils.trouveCandidatNumero(paireCandidats, 2);
			return true;
		}
		
		return false;	
	}
	
}
