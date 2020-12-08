package modele;

public class Case {
    private int numCase;
    private int xCase;
    private int yCase;
    private int valeur;
    private int region;
    private int etatCase;
    private CandidatsCase candidats = new CandidatsCase();
    public static final int INITIALE = -1;
    public static final int NON_TROUVEE = 0;
    public static final int TROUVEE = 1;
    
    
    public Case(int numCase, int x, int y) {
        this.setNumCase(numCase);
        this.setX(x);
        this.setY(y);
        this.setValeurCase(0);
        this.etatCase = NON_TROUVEE ;
        this.setRegion(Utils.calculNumeroRegion(numCase));
    }
    
    public int getNumCase() {return numCase;}
    public void setNumCase(int numCase) {this.numCase = numCase;}
    public int getxCase() {return xCase;}
    public void setX(int xCase) {this.xCase = xCase;}
    public int getyCase() {return yCase;}
    public void setY(int yCase) {this.yCase = yCase;}
    public int getValeur() {return valeur;}
    public void setValeurCase(int valeur) {this.valeur = valeur; this.etatCase = TROUVEE;}
    public boolean isCaseInitiale() {return this.etatCase == INITIALE;}
    public boolean nEstPasCaseInitiale() {return (this.etatCase != INITIALE);}
    public void setCaseInitiale() {this.etatCase = INITIALE;}
    public boolean isCaseTrouvee() {return this.etatCase == TROUVEE;}
    public boolean isCaseATrouver() { return this.etatCase == NON_TROUVEE;}
    public boolean nEstPasCaseTrouvee() {return (this.etatCase != TROUVEE);}
    public boolean isCandidat(int rang) {return candidats.isCandidat(rang);} 
    public int getNombreCandidats () {return candidats.getNombreCandidats();}
    public int getRegion() {return region;}
    public void setRegion(int region) {this.region = region;}
    public boolean [] getCandidatsTabBoolean() {return candidats.getCandidats();}
    public CandidatsCase getCandidats() {return this.candidats;}
    public boolean contientCandidatUnique() {return candidats.getNombreCandidats() == 1;}
    public int calculValeurUnique() {return candidats.calculValeurUnique();}
    public String construitLibelleCandidats() {return candidats.construitLibelleCandidats();}
    public void elimineCandidat(int valeur) {candidats.elimineCandidat(valeur);}
}