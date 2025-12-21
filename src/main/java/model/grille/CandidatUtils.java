package model.grille;

public class CandidatUtils {

    public static int getNombreCandidats(Grille grille, int numCase) {
        return grille.getCase(numCase).getNombreCandidats();
    }

    public static boolean [] calculOuLogique2Candidats(boolean[] candidats1 , boolean[] candidats2) {
        boolean[] resultat = new boolean [10];
        for (int i=0;i<resultat.length;i++) {
            resultat [i] = (candidats1[i] || candidats2[i]);
        }
        return resultat;
    }

    public static CandidatsCase calculEtLogique2Candidats(CandidatsCase candidats1, CandidatsCase candidats2) {
        CandidatsCase resultat = new CandidatsCase();
        for (int i=1;i<10;i++) {
            if (!(candidats1.isCandidat(i) && candidats2.isCandidat(i)))
                resultat.elimineCandidat(i);
        }
        return resultat;
    }

    public static boolean isIntersectionVide(CandidatsCase candidats1, CandidatsCase candidats2) {
        CandidatsCase resultat = calculEtLogique2Candidats(candidats1, candidats2);
        return (resultat.toString().equals("000000000 / "));
    }

    public static CandidatsCase elimineCandidatsCase(CandidatsCase aEliminer, CandidatsCase cible) {
        // Crée une copie des candidats de la cible
        boolean[] candidatsResultat = cible.getCandidats().clone();
        // Parcourt les candidats à éliminer
        for (int i = 1; i <= 9; i++) {
            if (aEliminer.isCandidat(i)) {
                candidatsResultat[i] = false; // Élimine le candidat
            }
        }
        // Retourne un nouvel objet CandidatsCase avec les candidats mis à jour
        return new CandidatsCase(candidatsResultat);
    }

    public static int calculNombreCandidats(boolean[] candidats) {
        int resultat =0;
        for (boolean candidat : candidats) {
            if (candidat) resultat += 1;
        }
        return resultat;
    }

    public static int trouveCandidatNumero(CandidatsCase candidat, int rang) {
        int rangTrouve = 0;
        for (int i=1;i<10;i++) {
            if (candidat.isCandidat(i)) rangTrouve+=1;
            if (rangTrouve == rang) return i;
        }
        return 0;
    }

    public static int trouvePremierCandidat(CandidatsCase candidatsCase) {
        for (int i = 1; i <= 9; i++) { // On ignore l'index 0
            if (candidatsCase.isCandidat(i)) {
                return i; // Retourne le premier candidat trouvé
            }
        }
        return 0; // Aucun candidat actif trouvé

    }

    public static int[] getCandidatsActifs(CandidatsCase candidatsCase) {
        // Compter le nombre de candidats actifs
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (candidatsCase.isCandidat(i)) {
                count++;
            }
        }

        // Créer le tableau et le remplir
        int[] actifs = new int[count];
        int index = 0;
        for (int i = 1; i <= 9; i++) {
            if (candidatsCase.isCandidat(i)) {
                actifs[index++] = i;
            }
        }

        return actifs;
    }
}


