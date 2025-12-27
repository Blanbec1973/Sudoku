package resolution.candidatunique;

import model.grille.CaseContext;
import model.grille.Grille;
import org.springframework.core.annotation.Order;
import resolution.MethodeResolution;
import resolution.ResolutionAction;
import resolution.ResolutionMethod;
import resolution.ZoneType;

import java.util.Optional;
import java.util.function.BiPredicate;
@ResolutionMethod(zones = {ZoneType.LIGNE, ZoneType.COLONNE, ZoneType.BLOC})
@Order(2)
public class CandidatUniqueDansZone extends MethodeResolution {
    private final BiPredicate<CaseContext, Integer> presenceChecker;

    private final ZoneType zone;

    public CandidatUniqueDansZone(Grille grille, ZoneType zone) {
        super(grille);
        this.zone = zone;
        switch(zone) {
            case LIGNE -> this.presenceChecker = grille.getGrilleService()::checkPresenceCandidatLigne;
            case COLONNE -> this.presenceChecker = grille.getGrilleService()::checkPresenceCandidatColonne;
            case BLOC -> this.presenceChecker = grille.getGrilleService()::checkPresenceCandidatRegion;
            default -> throw new IllegalArgumentException("ZoneType non supporté : " + zone);
        }
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
