package resolution.paireconjuguee;

import model.grille.CaseContext;
import model.grille.Grille;
import resolution.MethodeResolution;
import resolution.ResolutionAction;
import resolution.ZoneType;
import model.grille.CandidatUtils;
import utils.Utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PaireConjugueeDansZone extends MethodeResolution {
    private final Function<CaseContext, Optional<CaseContext>> configurationChecker;
    private final BiFunction<CaseContext, CaseContext, Optional<ResolutionAction>> eliminationChecker;
    private final ZoneType zone;

    public PaireConjugueeDansZone(Grille grille,
                                  ZoneType zone) {
        super(grille);

        switch (zone) {
            case LIGNE -> {
                this.configurationChecker = this::checkSamePairInLigne;
                this.eliminationChecker = this::searchCandidateInLigne;
            }
            case COLONNE -> {
                this.configurationChecker = this::checkSamePairInColumn;
                this.eliminationChecker = this::searchCandidateInColumn;
            }
            case BLOC -> {
                this.configurationChecker = this::checkSamePairInBloc;
                this.eliminationChecker = this::searchCandidateInBloc;
            }
            default -> throw new IllegalArgumentException("ZoneType non supporté : " + zone);
        }

        this.zone = zone;
    }

    @Override
    public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
        if (grille.getNombreCandidats(context.getNumCase()) != 2) return Optional.empty();

        Optional<CaseContext> optContext2 = configurationChecker.apply(context);
        if (optContext2.isEmpty()) return Optional.empty();
        CaseContext context2 = optContext2.get();

        return eliminationChecker.apply(context, context2);
    }

    Optional <CaseContext> checkSamePairInLigne(CaseContext context) {
        for (int x = 0; x < 9; x++) {
            if ((x != context.getX()) && grille.isCaseATrouver(x, context.getY()) &&
                    Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                            grille.getCandidatsTabBoolean(x, context.getY()))) {
                    return Optional.of(new CaseContext(x, context.getY()));

            }
        }
        return Optional.empty();
    }
    Optional <CaseContext> checkSamePairInColumn(CaseContext context) {
        for (int y = 0; y < 9; y++) {
            if ((y != context.getY()) && grille.isCaseATrouver(context.getX(), y) &&
                    (Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                    grille.getCandidatsTabBoolean(context.getX(), y)))) {
                    return Optional.of(new CaseContext(context.getX(),y));

            }
        }
        return Optional.empty();
    }
    Optional <CaseContext> checkSamePairInBloc(CaseContext context) {
        for (int abs=context.getxRegion();abs<context.getxRegion()+3;abs++) {
            for (int ord=context.getyRegion();ord<context.getyRegion()+3;ord++) {
                if ((context.getX() != abs || context.getY() != ord) &&
                        grille.isCaseATrouver(abs, ord) &&
                        Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                                grille.getCandidatsTabBoolean(abs, ord))) {
                    return Optional.of(new CaseContext(abs, ord));
                }
            }
        }
        return Optional.empty();
    }

    Optional<ResolutionAction> searchCandidateInLigne(CaseContext context, CaseContext context2) {
        int y = context.getY();
        int c1 = CandidatUtils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 1);
        int c2 = CandidatUtils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 2);
        int[] candidats = {c1, c2};

        for (int abs = 0; abs < 9; abs++) {
            if (grille.isCaseATrouver(abs, y) && abs != context.getX() && abs != context2.getX()) {
                if (grille.isCandidat(abs, y, c1))
                    return Optional.of(new ResolutionAction(Utils.calculNumCase(abs, context.getY()),
                            null, c1, this, context, candidats));
                if (grille.isCandidat(abs, y, c2))
                    return Optional.of(new ResolutionAction(Utils.calculNumCase(abs, context.getY()),
                            null, c2, this, context, candidats));
            }
        }
        return Optional.empty();
    }

    Optional<ResolutionAction> searchCandidateInColumn(CaseContext context, CaseContext context2) {
        int x = context.getX();
        int c1 = CandidatUtils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 1);
        int c2 = CandidatUtils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 2);
        int[] candidats = {c1, c2};

        for (int ord = 0; ord < 9; ord++) {
            if (grille.isCaseATrouver(x, ord) && ord != context.getY() && ord != context2.getY()) {
                if (grille.isCandidat(x, ord, c1))
                    return Optional.of(new ResolutionAction(Utils.calculNumCase(x, ord),
                            null, c1, this, context, candidats));
                if (grille.isCandidat(x, ord, c2))
                    return Optional.of(new ResolutionAction(Utils.calculNumCase(x, ord),
                            null, c2, this, context, candidats));
            }
        }
        return Optional.empty();
    }
    Optional<ResolutionAction> searchCandidateInBloc(CaseContext context, CaseContext context2) {
        int c1 = CandidatUtils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 1);
        int c2 = CandidatUtils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 2);
        int[] candidats = {c1, c2};

        for (int xAction=context.getxRegion();xAction<context.getxRegion()+3;xAction++) {
            for (int yAction=context.getyRegion();yAction<context.getyRegion()+3;yAction++) {
                if (grille.isCaseATrouver(Utils.calculNumCase(xAction, yAction)) &&
                        !Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                                grille.getCandidatsTabBoolean(xAction, yAction))) {
                    if (grille.isCandidat(Utils.calculNumCase(xAction, yAction), c1) ) {
                        return Optional.of(new ResolutionAction(Utils.calculNumCase(xAction, yAction), null,
                                c1, this, context, candidats));
                    }
                    if (grille.isCandidat(Utils.calculNumCase(xAction, yAction), c2) ) {
                        return Optional.of(new ResolutionAction(Utils.calculNumCase(xAction, yAction), null,
                                c2, this, context, candidats));
                    }
                }
            }
        }
        return Optional.empty();
    }
    public ZoneType getZone() {
        return zone;
    }
}
