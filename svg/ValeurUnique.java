package resolution;


import java.util.Arrays;

public class ValeurUnique {
    private boolean [] verif = new boolean [9];
    private boolean traitementEstFini, trouveDansDernierBalayage;
    int numCase, solution;

    public void setTraitementEstFini(boolean traitementEstFini) {
        this.traitementEstFini = traitementEstFini;
    }
    
    public boolean isTraitementEstFini() {
        return traitementEstFini;
    }
    

    private void initVerif() {
        for (int i=0;i<9;i++) {verif[i]=false;}
    }
    
    private void setVerif(int i) {
        verif[i]=true;
    }
    
    // renvoit 0 si pas de valeur unique pour la case ou la valeur trouvée si valeur unique
    private int checkVerif() {
        //Comptage des False :
        int nombreFalse = 0, valeurPossible=0;
        for (int i=0;i<9;i++) {if (!verif[i]) {nombreFalse = nombreFalse+1; valeurPossible=i+1;}} 
        if (nombreFalse > 1) {return 0;}
        else {return valeurPossible;}
    }
    
    public ValeurUnique() {
        traitementEstFini = false;
        trouveDansDernierBalayage=false;
        numCase = 0; 
    }
    
    public boolean rechercheSuivante(Grille maGrille) {
        this.initVerif();
        solution = 0;
        numCase = numCase+1;
        maGrille.calculXYSearch(numCase);
        if (maGrille.getValeurCase(maGrille.getxSearch(), maGrille.getySearch())==0) {  //Trouve une case vide
            this.initVerif();
            for (int valeur=1;valeur<10;valeur++) {
                if (maGrille.checkPresenceValeurLigne(valeur, maGrille.getySearch())) {this.setVerif(valeur-1);}
                if (maGrille.checkPresenceValeurColonne(valeur, maGrille.getxSearch())) {this.setVerif(valeur-1);}
                if (maGrille.checkPresenceValeurRegion(valeur, maGrille.getxSearch(), maGrille.getySearch())) {this.setVerif(valeur-1);}
            }
            solution = this.checkVerif();
            System.out.println("Case à chercher : "+maGrille.getxSearch()+maGrille.getySearch()+" "+Arrays.toString(verif)+" - "+solution);
        }
        if (solution!=0) {
            maGrille.setValeurCase(maGrille.getxSearch(),maGrille.getySearch(), solution);
            maGrille.setTrouvee(maGrille.getxSearch(),maGrille.getySearch());
            trouveDansDernierBalayage = true;
            //maGrille.Display();
            return true;
        }
        else {
            //maGrille.updateCandidats(maGrille.getxSearch(), maGrille.getySearch(),verif);
        }
        if (numCase == 81) {
            numCase = 0;
            if (!trouveDansDernierBalayage) {this.setTraitementEstFini(true);}
            else {trouveDansDernierBalayage = false;}
        }
        return false;
    }

    public int getSolution() {
        return solution;
    }
    

 
    
}