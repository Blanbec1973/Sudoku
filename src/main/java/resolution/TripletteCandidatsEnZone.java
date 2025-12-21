package resolution;

import model.grille.*;
import org.apache.logging.log4j.Level;
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
    public static final String TRIPLETTE_NB_CASES = "Triplette : {} - NbCases : {}";
    public static final String BOUCLE_RANG = "Boucle : {} - rang : {}";
    public static final String TRIPLETTE_TROUVEE = "Triplette trouvée ! {}";
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
        logger.log(Level.DEBUG, "Context : {} {}", context.getNumCase(),
                grille.getCandidats(context.getNumCase()));
        logger.log(Level.DEBUG, "Candidats {} : {}", zone, candidats);

        //E3-1 : Construit tableau de CandidatsCase avec toutes les triplettes possibles (tous candidats de la zone)
        List<CandidatsCase> tabTriplettesInit = calculTriplettes(candidats);
        logger.log(Level.DEBUG, "{} {}",tabTriplettesInit.size(), tabTriplettesInit);

        //E3-2 : Filtre tableau de CandidatsCase en ne conservant que les triplette avec au moins un candidat
        // en commun avec context
        List<CandidatsCase> tabTriplettes = filterTriplettesWithContext(context, tabTriplettesInit);
        logger.log(Level.DEBUG, "{} {}",tabTriplettes.size(), tabTriplettes);

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
        logger.log(Level.DEBUG, "NbCaseATrouverEnBloc : {}",grilleService.calculNombreCaseATrouverDansBloc(context));
        return grilleService.calculNombreCaseATrouverDansBloc(context);
    }
    //Méthodes E2 :
    private CandidatsCase listeCandidatsEnLigne(CaseContext context) {
        CandidatsCase candidats = new CandidatsCase();
        for (int x=0; x<9; x++) {
            if (grille.isCaseTrouvee(Utils.calculNumCase(x, context.getY())) ||
                    GrilleUtils.isCaseInitiale(grille, Utils.calculNumCase(x, context.getY()))) {
                logger.log(Level.DEBUG, "Colonne {} candidat à éliminer : {}", x,
                        GrilleUtils.getValeurCase(grille, x, context.getY()));
                candidats.elimineCandidat(GrilleUtils.getValeurCase(grille, x, context.getY()));
            }
            logger.log(Level.DEBUG, "Candidats : {}",candidats);
        }
        return candidats;
    }
    private CandidatsCase listeCandidatsEnColonne(CaseContext context) {
        CandidatsCase candidats = new CandidatsCase();
        for (int y=0; y<9; y++) {
            if (grille.isCaseTrouvee(Utils.calculNumCase(context.getX(),y)) ||
                GrilleUtils.isCaseInitiale(grille, Utils.calculNumCase(context.getX(),y))) {
                logger.log(Level.DEBUG, "Ligne {} candidat à éliminer : {}", y,
                        GrilleUtils.getValeurCase(grille, context.getX(), y));
                candidats.elimineCandidat(GrilleUtils.getValeurCase(grille, context.getX(), y));
            }
            logger.log(Level.DEBUG, "Candidats : {}",candidats);
        }
        return candidats;
    }
    private CandidatsCase listeCandidatsEnBloc(CaseContext context) {
        CandidatsCase candidats = new CandidatsCase();
        int xRegion = context.getxRegion();
        int yRegion = context.getyRegion();
        logger.log(Level.DEBUG, "Avant listeCandidatsEnBloc, context : {} - {}-{}",context.getNumCase(), xRegion, yRegion);
        logger.log(Level.DEBUG, "    Context : {}",context);
        for (int abs = xRegion; abs < xRegion + 3; abs++) {
            for (int ord = yRegion; ord < yRegion + 3; ord++) {
                if (GrilleUtils.isCaseInitiale(grille, Utils.calculNumCase(abs, ord)) ||
                        grille.isCaseTrouvee(Utils.calculNumCase(abs, ord)))
                    candidats.elimineCandidat(GrilleUtils.getValeurCase(grille, abs, ord));
            }
        }
        logger.log(Level.DEBUG, "Fin listeCandidatsEnBloc, candidats : {}",candidats);
        return candidats;
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
        CandidatsCase candidatsContext = grille.getCandidats(context.getNumCase());

        for (CandidatsCase c: tabSource) {
            if (!CandidatUtils.isIntersectionVide(candidatsContext, c)) {
                triplettes.add(c);
            }
        }
        return triplettes;
    }
    // E4 :
    private Optional<ResolutionAction> detectionEnLigne(CaseContext context, List<CandidatsCase> tabSource) {
        for (CandidatsCase c : tabSource) {
            List <Integer> rangs = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                CandidatsCase res = CandidatUtils.calculEtLogique2Candidats(c, grille.getCandidats(Utils.calculNumCase(x, context.getY())));
                if (res.getNombreCandidats()>0 ) {
                    rangs.add(x);
                }
            }
            logger.log(Level.DEBUG, TRIPLETTE_NB_CASES,c, rangs.size());
            if (rangs.size() == 3) {
                logger.log(Level.DEBUG, TRIPLETTE_TROUVEE, c);
                Optional<ResolutionAction> resolutionAction = detectionCandidatAEliminerEnLigne(context, c, rangs);
                if (resolutionAction.isPresent()) return resolutionAction;
            }
        }
        return Optional.empty();
    }



    private Optional<ResolutionAction> detectionEnColonne(CaseContext context, List<CandidatsCase> tabSource) {
        for (CandidatsCase c : tabSource) {
            List <Integer> rangs = new ArrayList<>();
            for (int y = 0; y < 9; y++) {
                CandidatsCase res = CandidatUtils.calculEtLogique2Candidats(c, grille.getCandidats(Utils.calculNumCase(context.getX(),y)));
                if (res.getNombreCandidats()>0 ) {
                    rangs.add(y);
                }
            }
            logger.log(Level.DEBUG, TRIPLETTE_NB_CASES,c, rangs.size());
            if (rangs.size() == 3) {
                logger.log(Level.DEBUG, "Triplet founded ! {}", c);
                Optional<ResolutionAction> resolutionAction = detectionCandidatAEliminerEnColonne(context, c, rangs);
                if (resolutionAction.isPresent()) return resolutionAction;
            }
        }
        return Optional.empty();
    }
    private Optional<ResolutionAction> detectionEnBloc(CaseContext context, List<CandidatsCase> tabSource) {
        int xRegion = context.getxRegion();
        int yRegion = context.getyRegion();
        for (CandidatsCase c : tabSource) {
            List <Integer> rangs = new ArrayList<>();
            for (int abs = xRegion; abs < xRegion + 3; abs++) {
                for (int ord = yRegion; ord < yRegion + 3; ord++) {
                    CandidatsCase res = CandidatUtils.calculEtLogique2Candidats(c, grille.getCandidats(Utils.calculNumCase(abs, ord)));
                    if (res.getNombreCandidats()>0 ) {
                        rangs.add(Utils.calculNumCase(abs, ord));
                    }
                }
            }

            logger.log(Level.DEBUG, TRIPLETTE_NB_CASES,c, rangs.size());
            if (rangs.size() == 3) {
                logger.log(Level.DEBUG, TRIPLETTE_TROUVEE, c);
                Optional<ResolutionAction> resolutionAction = detectionCandidatAEliminerEnBloc(context, c, rangs);
                if (resolutionAction.isPresent()) return resolutionAction;
            }
        }
        return Optional.empty();
    }
    private Optional <ResolutionAction> detectionCandidatAEliminerEnColonne(CaseContext context, CandidatsCase c, List<Integer> rangs) {
        for (int i=0; i < 3; i++) {
            logger.log(Level.DEBUG, BOUCLE_RANG, i, rangs.get(i));
            CandidatsCase c1 = CandidatUtils.elimineCandidatsCase(c, grille.getCandidats(Utils.calculNumCase(context.getX(), rangs.get(i))));
            if (c1.getNombreCandidats() > 0) {
                int candidatAEliminer = CandidatUtils.trouvePremierCandidat(c1);
                return Optional.of(new ResolutionAction(Utils.calculNumCase(context.getX(), rangs.get(i)), null,
                        candidatAEliminer, this, context, CandidatUtils.getCandidatsActifs(c)));

            }
        }
        return Optional.empty();
    }

    private Optional<ResolutionAction> detectionCandidatAEliminerEnLigne(CaseContext context, CandidatsCase c, List<Integer> rangs) {
        for (int i=0; i < 3; i++) {
            logger.log(Level.DEBUG, BOUCLE_RANG, i, rangs.get(i));
            CandidatsCase c1 = CandidatUtils.elimineCandidatsCase(c, grille.getCandidats(Utils.calculNumCase(rangs.get(i), context.getY())));
            if (c1.getNombreCandidats() > 0) {
                int candidatAEliminer = CandidatUtils.trouvePremierCandidat(c1);
                return Optional.of(new ResolutionAction(Utils.calculNumCase(rangs.get(i), context.getY()), null,
                        candidatAEliminer, this, context, CandidatUtils.getCandidatsActifs(c)));

            }
        }
        return Optional.empty();
    }

    private Optional<ResolutionAction> detectionCandidatAEliminerEnBloc(CaseContext context, CandidatsCase c, List<Integer> rangs) {
        for (int i=0; i < 3; i++) {
            logger.log(Level.DEBUG, BOUCLE_RANG, i, rangs.get(i));
            CandidatsCase c1 = CandidatUtils.elimineCandidatsCase(c, grille.getCandidats(rangs.get(i)));
            if (c1.getNombreCandidats() > 0) {
                int candidatAEliminer = CandidatUtils.trouvePremierCandidat(c1);
                return Optional.of(new ResolutionAction(rangs.get(i), null,
                        candidatAEliminer, this, context, CandidatUtils.getCandidatsActifs(c)));
            }
        }
        return Optional.empty();
    }
}
