package model.grille;

import utils.Utils;

public class CaseEnCours {
	private static int xSearch;
	private static int ySearch;
	private static int numCase;
	private static int xRegion;
	private static int yRegion;
	private static int numRegion;
	
	private CaseEnCours() {
		throw new IllegalStateException("Utility class");
	}
	
	public static int getXSearch() {return xSearch;}
	public static int getYSearch() {return ySearch;}
	public static String getXSearchEdition() {return String.valueOf(xSearch+1);}
	public static String getYSearchEdition() {return String.valueOf(ySearch+1);}
	public static int getNumCase() {return numCase;}
	public static int getxRegion() {return xRegion;}
	public static int getyRegion() {return yRegion;}
	public static int getNumRegion() {return numRegion;}
	public static void setCaseEnCours(int numCase) {
        xSearch = Utils.calculXsearch(numCase);
        ySearch = Utils.calculYsearch(numCase);
        CaseEnCours.setXYRegion();
        CaseEnCours.numCase = numCase;
		numRegion=Utils.calculNumeroRegion(numCase);
	}
	public static void setCaseEnCours(int x, int y) {
		xSearch = x;
		ySearch = y;
		numCase = Utils.calculNumCase(x, y);
		CaseEnCours.setXYRegion();
		numRegion=Utils.calculNumeroRegion(numCase);
	}
	private static void setXYRegion() {
		if (xSearch < 3) xRegion = 0;
		if (xSearch > 2 && xSearch < 6) xRegion = 3;
		if (xSearch > 5) xRegion = 6;
        
		if (ySearch < 3) yRegion = 0;
		if (ySearch > 2 && ySearch < 6) yRegion = 3;
		if (ySearch > 5) yRegion = 6;
    }

}