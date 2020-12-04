package modele;

public class CaseEnCours {
	private static int xSearch;
	private static int ySearch;
	private static int numCase;
	private static int xRegion;
	private static int yRegion;
	
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
	public static void setCaseEnCours(int numCase) {
        xSearch = Utils.calculXsearch(numCase);
        ySearch = Utils.calculYsearch(numCase);
        CaseEnCours.setXYRegion();
        CaseEnCours.numCase = numCase;
	}
	public static void setCaseEnCours(int x, int y) {
		xSearch = x;
		ySearch =y;
		numCase = Utils.calculNumCase(x, y);
	}
	private static void setXYRegion() {        
        switch (xSearch) {
        	case 0,1,2 -> xRegion=0;
            case 3,4,5 -> xRegion=3;
            case 6,7,8 -> xRegion=6;   
            default -> throw new IllegalStateException("xRegion incalculable");
        }
        switch (ySearch) { 
        	case 0,1,2 -> yRegion=0;
            case 3,4,5 -> yRegion=3;
            case 6,7,8 -> yRegion=6;
            default -> throw new IllegalStateException("yRegion incalculable");
        }
    }
}