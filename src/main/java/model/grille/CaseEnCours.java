package model.grille;

import utils.Utils;

public class CaseEnCours {
	private static int x;
	private static int y;
	private static int numCase;
	private static int xRegion;
	private static int yRegion;
	private static int numRegion;
	
	private CaseEnCours() {
		throw new IllegalStateException("Utility class");
	}
	
	public static int getX() {return x;}
	public static int getY() {return y;}
	public static String getXEdition() {return String.valueOf(x +1);}
	public static String getYEdition() {return String.valueOf(y +1);}
	public static int getNumCase() {return numCase;}
	public static int getxRegion() {return xRegion;}
	public static int getyRegion() {return yRegion;}
	public static int getNumRegion() {return numRegion;}
	public static void setCaseEnCours(int numCase) {
        x = Utils.calculXsearch(numCase);
        y = Utils.calculYsearch(numCase);
        CaseEnCours.calculeXYRegion();
        CaseEnCours.numCase = numCase;
		numRegion=Utils.calculNumeroRegion(numCase);
	}
	public static void setCaseEnCours(int x, int y) {
		CaseEnCours.x = x;
		CaseEnCours.y = y;
		numCase = Utils.calculNumCase(x, y);
		CaseEnCours.calculeXYRegion();
		numRegion=Utils.calculNumeroRegion(numCase);
	}
	private static void calculeXYRegion() {
		if (x < 3) xRegion = 0;
		if (x > 2 && x < 6) xRegion = 3;
		if (x > 5) xRegion = 6;
        
		if (y < 3) yRegion = 0;
		if (y > 2 && y < 6) yRegion = 3;
		if (y > 5) yRegion = 6;
    }

}