package resolution.candidatunique;

import model.grille.CaseContext;
import model.grille.Grille;
import resolution.MethodeResolution;
import resolution.ResolutionAction;
import resolution.ZoneType;

import java.util.Optional;
import java.util.function.BiPredicate;

public class CandidatUniqueDansZone extends MethodeResolution {
    private final BiPredicate<CaseContext, Integer> presenceChecker;

    private final ZoneType zone;

    public CandidatUniqueDansZone(Grille grille, BiPredicate<CaseContext, Integer> presenceChecker, ZoneType zone) {
        super(grille);
        this.presenceChecker = presenceChecker;
        this.zone = zone;
    }

    @Override
    public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
        for (int candidat = 1; candidat <= 9; candidat++) {
            if (grille.isCandidat(context.getNumCase(), candidat) && !presenceChecker.test(context, candidat)) {
                return Optional.of(new ResolutionAction(context.getNumCase(), candidat,
                        null, this, context, null));
            }
        }
        return Optional.empty();
    }
    public ZoneType getZone() {
        return zone;
    }
}
