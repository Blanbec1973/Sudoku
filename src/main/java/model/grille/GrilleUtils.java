package model.grille;

public class GrilleUtils {
    private GrilleUtils() {
        throw new IllegalStateException("Utility class");
    }
    //Questions sur les cases :
    public static int getValeurCase(Grille grille, int numCase) {
        return grille.getCase(numCase).getValeur();
    }

    public static int getValeurCase(Grille grille, int x, int y) {
        return grille.getCase(x,y).getValeur();
    }
    public static boolean isCaseInitiale(Grille grille, int numCase) {
        return grille.getCase(numCase).isCaseInitiale();
    }

    public static int getRegion(Grille grille, int numCase) {
        return grille.getCase(numCase).getRegion();
    }

    public static  boolean contientCandidatUnique(Grille grille, int numCase) {
        return grille.getCase(numCase).contientCandidatUnique();
    }
    public static int calculValeurUnique(Grille grille, int numCase) {
        return grille.getCase(numCase).calculValeurUnique();
    }
}
