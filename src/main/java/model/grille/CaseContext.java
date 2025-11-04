package model.grille;

import utils.Utils;

public class CaseContext {
    private final int numCase;
    private final int x;
    private final int y;
    private final int numRegion;
    private int xRegion;
    private int yRegion;

    public CaseContext(int numCase) {
        this.numCase = numCase;
        this.x = Utils.calculXsearch(numCase);
        this.y = Utils.calculYsearch(numCase);
        this.numRegion = Utils.calculNumeroRegion(numCase);
        calculeXYRegion();
    }
    public CaseContext(int x, int y) {
        this.numCase = Grille.calculNumCase(x,y);
        this.x = x;
        this.y = y;
        this.numRegion = Utils.calculNumeroRegion(numCase);
        calculeXYRegion();
    }
    public int getNumCase() {
        return numCase;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getNumRegion() {
        return numRegion;
    }
    public int getxRegion() {
        return xRegion;
    }
    public int getyRegion() {
        return yRegion;
    }
    public String getXEdition() {return String.valueOf(x +1);}
    public String getYEdition() {return String.valueOf(y +1);}

    private void calculeXYRegion() {
        if (x < 3) xRegion = 0;
        if (x > 2 && x < 6) xRegion = 3;
        if (x > 5) xRegion = 6;

        if (y < 3) yRegion = 0;
        if (y > 2 && y < 6) yRegion = 3;
        if (y > 5) yRegion = 6;
    }
}