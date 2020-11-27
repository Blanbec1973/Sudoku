package modele;

public class Case {
    private int numCase, xCase, yCase, valeur, region;
    private int etatCase;
    private Candidats candidats = new Candidats();
    public static final int caseInitiale = -1;
    public static final int caseNonTrouvee = 0;
    public static final int caseTrouvee = 1;
    
    
    public Case(int numCase, int x, int y) {
        this.setNumCase(numCase);
        this.setX(x);
        this.setY(y);
        this.setValeurCase(0);
        this.etatCase = caseNonTrouvee ;
        this.setRegion(Utils.calculNumeroRegion(numCase));
    }
    
    public int getNumCase() {return numCase;}
    public void setNumCase(int numCase) {this.numCase = numCase;}
    public int getxCase() {return xCase;}
    public void setX(int xCase) {this.xCase = xCase;}
    public int getyCase() {return yCase;}
    public void setY(int yCase) {this.yCase = yCase;}
    public int getValeur() {return valeur;}
    public void setValeurCase(int valeur) {this.valeur = valeur; this.etatCase = caseTrouvee;}
    public boolean isCaseInitiale() {return this.etatCase == caseInitiale;}
    public boolean nEstPasCaseInitiale() {return !(this.etatCase == caseInitiale);}
    public void setCaseInitiale() {this.etatCase = caseInitiale;}
    public boolean isCaseTrouvee() {return this.etatCase == caseTrouvee;}
    public boolean isCaseATrouver() { return this.etatCase == caseNonTrouvee;}
    public boolean nEstPasCaseTrouvee() {return !(this.etatCase == caseTrouvee);}
    public boolean isCandidat(int rang) {return candidats.isCandidat(rang);} 
    public int getNombreCandidats () {return candidats.getNombreCandidats();}
    public int getRegion() {return region;}
    public void setRegion(int region) {this.region = region;}
    public boolean [] getCandidatsTabBoolean() {return candidats.getCandidats();}
    public Candidats getCandidats() {return this.candidats;}
    public boolean contientCandidatUnique() {return candidats.getNombreCandidats() == 1;}
    public int calculValeurUnique() {return candidats.calculValeurUnique();}
    public String construitLibelleCandidats() {return candidats.construitLibelleCandidats();}
    public void elimineCandidats(boolean[] candidatsAEliminer) {candidats.elimineCandidats(candidatsAEliminer);}
    public void elimineCandidat(int valeur) {candidats.elimineCandidat(valeur);}



}