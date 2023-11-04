package model.grille;

import utils.Utils;

public class Case {
    private int numCase;
    private int xCase;
    private int yCase;
    private int valeur;
    private int region;
    private EtatCase etatCase;
	private final CandidatsCase candidats = new CandidatsCase();
    
    public Case(int numCase, int x, int y) {
        this.setNumCase(numCase);
        this.setX(x);
        this.setY(y);
        this.etatCase = EtatCase.NOT_FOUNDED;
        this.setRegion(Utils.calculNumeroRegion(numCase));
    }
    
    public int getNumCase() {return numCase;}
    public void setNumCase(int numCase) {this.numCase = numCase;}
    public int getxCase() {return xCase;}
    public void setX(int xCase) {this.xCase = xCase;}
    public int getyCase() {return yCase;}
    public void setY(int yCase) {this.yCase = yCase;}
    public int getValeur() {return valeur;}
    public void setValeurCase(int valeur)
        {
            this.valeur = valeur;
            this.etatCase = EtatCase.FOUNDED;
            candidats.setAllCandidatsToFalse();
        }
    public int getRegion() {return region;}
    public void setRegion(int region) {this.region = region;}
    public EtatCase getEtatCase() {return etatCase;}
	public void setEtatCase(EtatCase etatCase) {this.etatCase = etatCase;}
    public boolean isCaseInitiale() {return this.etatCase == EtatCase.INITIAL;}
    public boolean nEstPasCaseInitiale() {return (this.etatCase != EtatCase.INITIAL);}
    public void setCaseInitiale(int valeur)
        {
            this.valeur = valeur;
            this.etatCase = EtatCase.INITIAL;
            candidats.setAllCandidatsToFalse();
        }
    public boolean isCaseTrouvee() {return this.etatCase == EtatCase.FOUNDED;}
    public boolean isCaseATrouver() { return this.etatCase == EtatCase.NOT_FOUNDED;}
    public boolean nEstPasCaseTrouvee() {return (this.etatCase != EtatCase.FOUNDED);}
    public boolean isCandidat(int rang) {return candidats.isCandidat(rang);} 
    public int getNombreCandidats () {return candidats.getNombreCandidats();}
    public boolean [] getCandidatsTabBoolean() {return candidats.getCandidats();}
    public CandidatsCase getCandidats() {return this.candidats;}
    public boolean contientCandidatUnique() {return candidats.getNombreCandidats() == 1;}
    public int calculValeurUnique() {return candidats.calculValeurUnique();}
    public String construitLibelleCandidats() {return candidats.construitLibelleCandidats();}
    public void elimineCandidat(int valeur) {candidats.elimineCandidat(valeur);}
}