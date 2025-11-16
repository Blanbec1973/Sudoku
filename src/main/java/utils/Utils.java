package utils;

import model.grille.CandidatsCase;

public class Utils {
	
	private Utils() {
		throw new IllegalStateException("Utility class");
	}
	
    public static int calculXsearch(int numCase) {
        int reste = numCase % 9;
        if (reste == 0) {return 8;} else {return reste - 1;}
    }
    
    public static int calculYsearch(int numCase) {
        int division = numCase / 9;
        int reste = numCase % 9;
        if (reste == 0) {return division-1;} else {return division;}
    }
        
    public static int calculNumeroRegion(int numCase) {
        int division = numCase / 9;
        int reste = numCase % 9;
        int region = 0;
        
        if (reste==0) {
        	if (division < 4) return 3;
        	if (division < 7) return 6;
        	return 9;
        }
        
        if (division < 3) region = 1;
        if (division > 2 && division < 6) region = 4;
        if (division > 5) region = 7;
           
        if (reste < 4) return region;
        if (reste < 7) return region+1;
        return region+2;
    }
    


    public static int calculNombreTriplettes(int nombreCasesATrouver) {
        int resultat;
        int i;
        i = nombreCasesATrouver -2;
        resultat = i;
        while (i!=0) resultat = resultat + --i;
        return resultat;
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

    public static int calculAutresLignesOuColonnesDuneRegion(int rang, int autreRang) {
        if (rang == 0 || rang == 3 || rang == 6) return rang + autreRang;
        if (rang == 2 || rang == 5 || rang == 8) {
            if (autreRang == 1) {
                return rang - 2;
            } else {
                return rang - 1;
            }
        }
        if (rang == 1 || rang == 4 || rang == 7) {
            if (autreRang == 1) {
                return rang - 1;
            } else {
                return rang + 1;
            }
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
