package model.grille;

import java.util.Arrays;

public class CandidatsCase {
    private boolean[] candidats  = new boolean [10];
    private int nombreCandidats;
    
    public CandidatsCase() {
        setAllCandidatsToTrue();
    }
    protected void setAllCandidatsToTrue() {
        Arrays.fill(this.candidats, true);
        this.elimineCandidat(0);
        this.calculNombreCandidats();
    }
    public CandidatsCase (boolean [] entree) {this.candidats = entree; this.calculNombreCandidats();}
    public boolean[] getCandidats() {return candidats;}
    public int getNombreCandidats() {return nombreCandidats;}
    public void setCandidats(boolean[] candidats) {this.candidats = candidats; this.calculNombreCandidats();}
    public void setCandidat(int rang) {
        if (rang < 0 || rang > 9) {
            throw new IllegalArgumentException("Le rang du candidat doit être compris entre 0 et 9.");
        }
        this.candidats[rang] = true;
        this.calculNombreCandidats();}
    public boolean isCandidat(int rang) {return candidats[rang];}
    public String toString() {
    	StringBuilder bld = new StringBuilder();
        StringBuilder bld2 = new StringBuilder(" / ");
    	for (int i=1;i<10;i++ ) {
    		if (this.isCandidat(i)) {
                bld.append("1");
                bld2.append(i);
            } else bld.append("0");
    	}
        bld.append(bld2);
    	return bld.toString();
    }
    public void setAllCandidatsToFalse() {
        for (int i=1;i<10;i++) {this.candidats[i]=false;}
        this.calculNombreCandidats();
    }
    public void elimineCandidat(int valeur) {
        if (!this.candidats[valeur]) {return;}
        this.candidats[valeur]=false; 
        this.calculNombreCandidats();
    }
    private int compterCandidatsActifs() {
        int count = 0;
        for (int i = 1; i < 10; i++) {
            if (candidats[i]) count++;
        }
        return count;
    }
    private void calculNombreCandidats() {
        this.nombreCandidats = compterCandidatsActifs();
    }
    public String construitLibelleCandidats() {
        return "<html>" +
                editeCandidat(1) + editeCandidat(2) + editeCandidat(3) + "<br>" +
                editeCandidat(4) + editeCandidat(5) + editeCandidat(6) + "<br>" +
                editeCandidat(7) + editeCandidat(8) + editeCandidat(9) +
                "</html>";
    }
    private String editeCandidat(int candidat) {
    	if (this.isCandidat(candidat)) return String.valueOf(candidat);
    	return " ";
    }
    public boolean contientCandidatUnique() {
        return this.nombreCandidats == 1;
    }
    public int calculValeurUnique() {
        if (this.nombreCandidats != 1) {
            throw new IllegalStateException("La case ne contient pas un candidat unique.");
        }
        for (int i = 1; i < 10; i++) {
            if (this.isCandidat(i)) return i;
        }
        throw new IllegalStateException("Aucun candidat actif trouvé malgré nombreCandidats == 1.");
    }
}
