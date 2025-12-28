package model.grille;

import java.util.stream.IntStream;

public class CopyGrilleService {

    private CopyGrilleService() throws InstantiationException {
        throw new InstantiationException("Utility class : "+this.getClass().getName());
    }
    public static void copyGrille(Grille source, Grille cible) {
        // grille : tableau de Cases + liste casesATrouver
        //     Case : numCase, xCase, yCase, valeur, region, etatCase, candidats
        //			Candidats : candidats, nombreCandidats

        cible.getCasesAtrouver().clear();
        source.getCasesAtrouver().forEach(numCase -> cible.getCasesAtrouver().add(numCase));

        IntStream.rangeClosed(1, 81).forEach(i-> {
            cible.getCase(i).setCaseTrouvee(GrilleUtils.getValeurCase(source, i));
            cible.getCase(i).setEtatCase(source.getCase(i).getEtatCase());
            copyCandidats(source.getCase(i).getCandidats(), cible.getCase(i).getCandidats());
        });

    }
    private static void copyCandidats(CandidatsCase input, CandidatsCase output) {
        boolean[] candidatsInput = input.getCandidats();
        output.setCandidats(candidatsInput.clone());
    }
}
