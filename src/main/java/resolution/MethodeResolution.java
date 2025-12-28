package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import model.service.GrilleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

//Méthodes de résolution par ordre hiérarchique :
//Méthodes qui permettent de trouver une case : 
//    CandidatUniqueDansCase    : La case n'a plus qu'un candidat
//	  CandidatUniqueDansLigne   : un candidat de la case n'existe pas dans les autres cases de la ligne
//    CandidatUniqueDansColonne : un candidat de la case n'existe pas dans les autres cases de la colonne
//    CandidatUniqueDansRegion  : un candidat de la case n'existe pas dans les autres cases de la région
//
//Méthodes qui permettent d'éliminer des candidats :
//  PaireCandidats2CasesColonne : paire de candidats seulement dans 2 cases d'un colonne.
//	Recherche d'absence de candidats en colonne dans les autres régions.
//	Recherche de triplette de candidat dans 3 cases en ligne : 
//	Case en cours avec 2 candidats seulement : 
//     PaireConjugueeEnLigne   : une case de le ligne comporte les mêmes 2 candidats que la caseEnCours
//     PaireConjugueeEnColonne : une case de le colonne comporte les mêmes 2 candidats que la caseEnCours
//     PaireConjugueeEnRegion  : une case de le région comporte les mêmes 2 candidats que la caseEnCours
// Candidat dans colonne unique d'une région
// Candidat dans ligne unique d'une région


public abstract class MethodeResolution {
	protected final Grille grille;
	protected final GrilleService grilleService;

	static final Logger logger = LoggerFactory.getLogger(MethodeResolution.class);

	protected MethodeResolution(Grille grille) {
		this.grille=grille;
		this.grilleService = grille.getGrilleService();
	}

	public Optional<ResolutionAction> detecteSuivant(boolean goPourChangement) {
		logger.debug("DetecteSuivant :");
        return grille.getCasesAtrouver().stream()
                .map(CaseContext::new)
                .map(context -> traiteCaseEnCours(context, goPourChangement))
                .flatMap(Optional::stream)
                .findFirst();
	}
	public abstract Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement);

	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}
	
}