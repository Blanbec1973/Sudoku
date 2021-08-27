package modele;

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
    
    public static int calculNombreCaseATrouverDansLigne(Grille maGrille, int ySearch) {
        int resultat = 0;
        for (int i=0;i<9;i++) {
            if (maGrille.getCase(i, ySearch).nEstPasCaseInitiale() && maGrille.getCase(i, ySearch).nEstPasCaseTrouvee())
                resultat+=1;
        }
        return resultat;
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
    
    public static boolean [] calculEtLogique2Candidats(boolean[] candidats1, boolean[] candidats2) {
        boolean[] resultat = new boolean [10];
        for (int i=0;i<resultat.length;i++) {
            resultat [i] = (candidats1[i] && candidats2[i]);
        }
        return resultat;
    }
    
    public static int calculNombreCandidats(boolean[] candidats) {
    	int resultat =0;
    	for (int i=0;i<candidats.length;i++) {
    		if (candidats[i]) resultat+=1;
    	}
    	return resultat;
    }
    
    public static int calculNumCase(int x, int y) {
    	return (y*9+x+1);
    }

	public static int trouveCandidatNumero(CandidatsCase candidat, int rang) {
		int rangTrouve = 0;
		for (int i=1;i<10;i++) {
			if (candidat.isCandidat(i)) rangTrouve+=1;
			if (rangTrouve == rang) return i;
		}
			
		return 0;
	}
}
