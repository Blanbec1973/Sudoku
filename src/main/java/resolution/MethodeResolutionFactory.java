package resolution;

import model.grille.Grille;
import resolution.candidatunique.CandidatUniqueDansZone;
import resolution.paireconjuguee.PaireConjugueeDansZone;

import java.util.ArrayList;
import java.util.List;

public class MethodeResolutionFactory {

    private MethodeResolutionFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static List<MethodeResolution> createAll(Grille grille) {
        List<MethodeResolution> methodes = new ArrayList<>();

        // Méthodes de base
        methodes.add(new CandidatUniqueDansCase(grille));
        methodes.add(new CandidatUniqueDansZone(grille, grille.getGrilleService()::checkPresenceCandidatLigne, ZoneType.LIGNE));
        methodes.add(new CandidatUniqueDansZone(grille, grille.getGrilleService()::checkPresenceCandidatColonne, ZoneType.COLONNE));
        methodes.add(new CandidatUniqueDansZone(grille, grille.getGrilleService()::checkPresenceCandidatRegion, ZoneType.BLOC));

        // Paires
        methodes.add(new PaireCandidats2CasesColonne(grille));
        methodes.add(new PaireCandidats2CasesLigne(grille));
        methodes.add(new PaireConjugueeDansZone(grille, ZoneType.LIGNE));
        methodes.add(new PaireConjugueeDansZone(grille, ZoneType.COLONNE));
        methodes.add(new PaireConjugueeDansZone(grille, ZoneType.BLOC));

        // Absences
        methodes.add(new AbsenceCandidatEnColonneDansLesAutresRegions(grille));
        methodes.add(new AbsenceCandidatEnLigneDansLesAutresRegions(grille));

        // Triplettes
        methodes.add(new TripletteCandidatsEnLigne(grille));
        methodes.add(new TripletteCandidatsEnZone(grille, ZoneType.COLONNE));

        // Candidat dans zone unique
        methodes.add(new CandidatDansColonneUniqueDuneRegion(grille));
        methodes.add(new CandidatDansLigneUniqueDuneRegion(grille));

        return methodes;
    }
}
