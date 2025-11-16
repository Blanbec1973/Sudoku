package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;


// Si dans une zone, 3 candidats ne se trouvent que dans 3 cases de la zone, alors on peut éliminer tous les autres
// candidats de ces cases.
// Méthode inutile s'il y a moins de 4 cases à trouver dans la zone.


public class TripletteCandidatsEnZone extends MethodeResolution {
    private final ZoneType zone;
    private final Function<CaseContext, Integer> controleChecker;
    private final Function<CaseContext, CandidatsCase> listeCandidatsChecker;
    private final BiFunction<CaseContext, List<CandidatsCase>, Optional<ResolutionAction>> detectionChecker;

    public TripletteCandidatsEnZone(Grille grille, ZoneType zone) {
        super(grille);
        this.zone = zone;
        switch (zone) {
            case LIGNE -> {
                this.controleChecker = this::checkNbCaseATrouverEnLigne;
                this.listeCandidatsChecker = this::listeCandidatsEnLigne;
                this.detectionChecker = this::detectionEnLigne;
            }
            case COLONNE -> {
                this.controleChecker = this::checkNbCaseATrouverEnColonne;
                this.listeCandidatsChecker = this::listeCandidatsEnColonne;
                this.detectionChecker = this::detectionEnColonne;
            }
            case BLOC -> {
                this.controleChecker = this::checkNbCaseATrouverEnBloc;
                this.listeCandidatsChecker = this::listeCandidatsEnBloc;
                this.detectionChecker = this::detectionEnBloc;
            }
            default -> throw new IllegalArgumentException("ZoneType non supporté : " + zone);
        }
    }

    public ZoneType getZone() {
        return zone;
    }

    @Override
    public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
        // E1 : S'il y a moins de 4 cases à trouver dans la zone, on sort :
        if (controleChecker.apply(context) <= 3) return Optional.empty();

        //E2 : Liste tous les candidats présents dans la zone :
        CandidatsCase candidats = listeCandidatsChecker.apply(context);
        logger.debug("Context : " + context.getNumCase() + " "+grille.getCandidats(context.getNumCase()).toString());
        logger.debug(("Candidats " + zone + " : "+candidats));

        //E3-1 : Construit tableau de CandidatsCase avec toutes les triplettes possibles (tous candidats de la zone)
        List<CandidatsCase> tabTriplettesInit = calculTriplettes(candidats);
        logger.debug((tabTriplettesInit.size() + " " + tabTriplettesInit));

        //E3-2 : Filtre tableau de CandidatsCase en ne conservant que les triplette avec au moins un candidat
        // en commun avec context
        List<CandidatsCase> tabTriplettes = filterTriplettesWithContext(context, tabTriplettesInit);
        logger.debug((tabTriplettes.size() + " " + tabTriplettes));

        //E4 : Détection d'une triplette et détermination d'un candidat à éliminer :
        return detectionChecker.apply(context, tabTriplettes);

    }
    //Méthodes E1 :
    private Integer checkNbCaseATrouverEnLigne(CaseContext context) {
        return grilleService.calculNombreCaseATrouverDansLigne(context.getY());
    }
    private Integer checkNbCaseATrouverEnColonne(CaseContext context) {
        return grilleService.calculNombreCaseATrouverDansColonne(context.getX());
    }
    private Integer checkNbCaseATrouverEnBloc(CaseContext context) {
        //TODO
        return 0;
    }
    //Méthodes E2 :
    private CandidatsCase listeCandidatsEnLigne(CaseContext context) {
        //TODO
        return null;
    }
    private CandidatsCase listeCandidatsEnColonne(CaseContext context) {
        CandidatsCase candidats = new CandidatsCase();
        logger.debug("Candidats initial : {}", candidats);
        for (int y=0; y<9; y++) {
            if (grille.isCaseTrouvee(Grille.calculNumCase(context.getX(),y)) ||
                grille.isCaseInitiale(Grille.calculNumCase(context.getX(),y))) {
                logger.debug(("Ligne "+ y + " candidat à éliminer : "+grille.getValeurCase(context.getX(), y)));
                candidats.elimineCandidat(grille.getValeurCase(context.getX(), y));
            }
            logger.debug(("Candidats : "+candidats));
        }
        return candidats;
    }
    private CandidatsCase listeCandidatsEnBloc(CaseContext context) {
        //TODO
        return null;
    }
    //Méthodes E3 :
    private List<CandidatsCase> calculTriplettes(CandidatsCase source) {

        List<Integer> candidatsActifs = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (source.isCandidat(i)) {
                candidatsActifs.add(i);
            }
        }

        List<CandidatsCase> triplettes = new ArrayList<>();
        int n = candidatsActifs.size();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    CandidatsCase triplette = new CandidatsCase();
                    triplette.setAllCandidatsToFalse();
                    triplette.setCandidat(candidatsActifs.get(i));
                    triplette.setCandidat(candidatsActifs.get(j));
                    triplette.setCandidat(candidatsActifs.get(k));
                    triplettes.add(triplette);
                }
            }
        }

        return triplettes;
    }
    private List<CandidatsCase> filterTriplettesWithContext(CaseContext context, List<CandidatsCase> tabSource) {
        List<CandidatsCase> triplettes = new ArrayList<>();
        CandidatsCase CandidatsContext = grille.getCandidats(context.getNumCase());

        for (CandidatsCase c: tabSource) {
            if (!Utils.isIntersectionVide(CandidatsContext, c)) {
                triplettes.add(c);
            }
        }

        return triplettes;
    }
    // E4 :
    private Optional<ResolutionAction> detectionEnLigne(CaseContext context, List<CandidatsCase> tabSource) {
        return Optional.empty();
    }
    private Optional<ResolutionAction> detectionEnColonne(CaseContext context, List<CandidatsCase> tabSource) {
        logger.debug(("icila2"));
        for (CandidatsCase c : tabSource) {
            logger.debug(("icila3"));
            List <Integer> rangs = new ArrayList<>();
            for (int y = 0; y < 9; y++) {
                CandidatsCase res = Utils.calculEtLogique2Candidats(c, grille.getCandidats(Grille.calculNumCase(context.getX(),y)));
                if (res.getNombreCandidats()>0 ) {
                    rangs.add(y);
                }
            }
            logger.debug(("Triplette : "+c.toString()+" - NbCases : "+rangs.size()));
            if (rangs.size() == 3) {
                logger.debug("Triplette trouvée ! " + c);
                Optional<ResolutionAction> resolutionAction = detectionCandidatAEliminerEnColonne(context, c, rangs);
                if (resolutionAction.isPresent()) return resolutionAction;
            }
        }
        return Optional.empty();
    }
    private Optional<ResolutionAction> detectionEnBloc(CaseContext context, List<CandidatsCase> tabSource) {
        return Optional.empty();
    }
    private Optional <ResolutionAction> detectionCandidatAEliminerEnColonne(CaseContext context, CandidatsCase c, List<Integer> rangs) {
        for (int i=0; i < 3; i++) {
            logger.debug("Boucle : " + i + " - rang : " + rangs.get(i));
            CandidatsCase c1 = Utils.elimineCandidatsCase(c, grille.getCandidats(Grille.calculNumCase(context.getX(), rangs.get(i))));
            if (c1.getNombreCandidats() > 0) {
                int candidatAEliminer = Utils.trouvePremierCandidat(c1);
                return Optional.of(new ResolutionAction(Grille.calculNumCase(context.getX(), rangs.get(i)), null,
                        candidatAEliminer, this, context, Utils.getCandidatsActifs(c)));

            }
        }
        return Optional.empty();
    }
}
