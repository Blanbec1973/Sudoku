
package modele;

public class Candidats {
    private boolean candidats [] = new boolean [10];
    private int nombreCandidats;
    
    public Candidats() {
        for (int i=0;i<10;i++) {this.candidats[i]=true;}
        this.nombreCandidats=10;
        this.elimineCandidat(0);
    }
    
    
    public Candidats (boolean [] entree) {this.candidats = entree; this.nombreCandidats = this.calculNombreCandidats();}

    public boolean[] getCandidats() {return candidats;}
    public int getNombreCandidats() {return nombreCandidats;}
    public void setCandidats(boolean[] candidats) {this.candidats = candidats; this.nombreCandidats = this.calculNombreCandidats();}
    public void setCandidat(int rang) {this.candidats[rang]=true;this.nombreCandidats+=1;}
    public boolean isCandidat(int rang) {return candidats[rang];}
    
    public boolean equals(Candidats c2) {
    	for(int i=1;i<10;i++) {
    		if (this.candidats[i]!=c2.candidats[i]) return false;
    	}
    	return true;
    }
    
    public String displayCandidats() {
    	String message = "";
    	for (int i=1;i<10;i++ ) {
    		if (this.isCandidat(i)) message+="1"; else message+="0";
    	}
    	return message;
    }
    
    public void setAllCandidatsToFalse() {
        for (int i=0;i<10;i++) {this.candidats[i]=false;}
        this.nombreCandidats=0;
    }
    
    public void elimineCandidat(int valeur) {
        if (!this.candidats[valeur]) {return;}
        this.candidats[valeur]=false; 
        this.nombreCandidats-=1;
    }
    
    void elimineCandidats(boolean[] candidatsAEliminer) {
        for (int ind=1;ind<10;ind++) {
            if (candidatsAEliminer[ind]) this.elimineCandidat(ind);
        }
    }
    
    public int calculNombreCandidats() {
        int resultat = 0;
        for (int i=0;i<10;i++) {
            if (this.candidats[i]) resultat +=1;
        }
        return resultat;
    }
    
    public String construitLibelleCandidats() {
        String libelle;
        libelle="<html>";
        if (candidats[1]) {libelle+="1";} else {libelle+=" ";}
        if (candidats[2]) {libelle+="2";} else {libelle+=" ";}
        if (candidats[3]) {libelle+="3<br>";} else {libelle+=" <br>";}
        if (candidats[4]) {libelle+="4";} else {libelle+=" ";}
        if (candidats[5]) {libelle+="5";} else {libelle+=" ";}
        if (candidats[6]) {libelle+="6<br>";} else {libelle+=" <br>";}
        if (candidats[7]) {libelle+="7";} else {libelle+=" ";}
        if (candidats[8]) {libelle+="8";} else {libelle+=" ";}
        if (candidats[9]) {libelle+="9";}else {libelle+=" ";}
        libelle+="</html>";
        return libelle;
    }

    boolean contientCandidatUnique() {
        return this.nombreCandidats == 1;
    }

    int calculValeurUnique() {
        int i = 1;
        while (!this.isCandidat(i)) {i+=1;}
        return i;
    }
    
}
