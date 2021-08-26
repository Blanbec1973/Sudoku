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
	private static void setxRegion(int xRegion) {CaseEnCours.xRegion=xRegion;}
	private static void setyRegion(int yRegion) {CaseEnCours.yRegion=yRegion;}
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
		CaseEnCours.setXYRegion();
	}
	private static void setXYRegion() {        
        switch (xSearch) {
        	case 0 : {setxRegion(0); break;}
        	case 1 : {setxRegion(0); break;}
        	case 2 : {setxRegion(0); break;}
            case 3 : {setxRegion(3); break;}
            case 4 : {setxRegion(3); break;}
            case 5 : {setxRegion(3); break;}
            case 6 : {setxRegion(6); break;}
            case 7 : {setxRegion(6); break;}
            case 8 : {setxRegion(6); break;}
            default : {throw new IllegalStateException("xRegion incalculable");}
        }
        switch (ySearch) { 
        	case 0 : {setyRegion(0); break;}
        	case 1 : {setyRegion(0); break;}
        	case 2 : {setyRegion(0); break;}
            case 3 : {setyRegion(3); break;}
            case 4 : {setyRegion(3); break;}
            case 5 : {setyRegion(3); break;}
            case 6 : {setyRegion(6); break;}
            case 7 : {setyRegion(6); break;}
            case 8 : {setyRegion(6); break;}
            default : {throw new IllegalStateException("yRegion incalculable");}
        }
    }
}