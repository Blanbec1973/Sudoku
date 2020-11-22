package modele;

import controleur.Controle;
import java.util.Arrays;

public class Algorithme {
    private boolean estTraitementFini, trouveDansDernierBalayage;
    private int numCase;
    private Controle controle;
    
    public Algorithme(Controle controle) {
        this.controle = controle;
        this.estTraitementFini=false;
        this.trouveDansDernierBalayage=false;
        numCase=0;
    }
    
    public void run(Grille grille) {
        while (!this.estTraitementFini) {
            numCase = numCase + 1;
            this.traitementCase(grille);
            if (numCase == 81) {
                numCase = 0;
                if (!this.trouveDansDernierBalayage) {this.estTraitementFini = true;}
                else {trouveDansDernierBalayage = false;}
            }
        }
    }

    private void traitementCase(Grille grille) {
        int xSearch, ySearch, solution;
        grille.calculXYSearchEtRegion(this.numCase);
        xSearch=grille.getxSearch();
        ySearch=grille.getySearch();
        controle.demandeSetFocusCase(xSearch, ySearch);
        /*if (numCase == 2) {
            System.out.println(String.valueOf(this.numCase));}
        */
        //Case initiale ?
        if (grille.getCaseEnCours().isCaseInitiale()) {return;}
        
        //Case déjà trouvée ?
        if (grille.getCaseEnCours().isCaseTrouvee()) {return;}        
        
        //Candidat unique dans la case ?
        if (grille.getCaseEnCours().contientCandidatUnique()) {
             solution = grille.getCaseEnCours().calculValeurUnique();
             this.trouveDansDernierBalayage = true;
             grille.setValeurCaseEnCours(solution);
             grille.elimineCandidatsCaseTrouvee(xSearch, ySearch, solution);
             controle.demandeAfficheCommande("Valeur unique case "+ String.valueOf(xSearch+1)+"-"+String.valueOf(ySearch+1)+" : "+solution );
             //javax.swing.JOptionPane.showMessageDialog(null,"Valeur unique case");
             return;
        }
        
        //Candidat unique dans la ligne ?
        for (int candidat=1;candidat<10;candidat++) {
            if (grille.getCaseEnCours().isCandidat(candidat) &&
                !grille.checkPresenceCandidatLigne(candidat, xSearch, ySearch)) {
                this.trouveDansDernierBalayage = true;
                grille.setValeurCaseEnCours(candidat);
                grille.elimineCandidatsCaseTrouvee(xSearch, ySearch, candidat);
                controle.demandeAfficheCommande("Candidat unique ligne "+String.valueOf(ySearch+1)+" : "+candidat);
                //javax.swing.JOptionPane.showMessageDialog(null,"Candidat unique Ligne : "+String.valueOf(candidat));
                return;
            }
        }
        
        //Candidat unique dans la colonne ?
        for (int candidat=1;candidat<10;candidat++) {
            if (grille.getCaseEnCours().isCandidat(candidat) &&
                !grille.checkPresenceCandidatColonne(candidat, xSearch, ySearch)) {
                this.trouveDansDernierBalayage = true;
                grille.setValeurCaseEnCours(candidat);
                grille.elimineCandidatsCaseTrouvee(xSearch, ySearch, candidat);
                controle.demandeAfficheCommande("Candidat unique colonne "+String.valueOf(xSearch+1)+" : "+candidat);
                //javax.swing.JOptionPane.showMessageDialog(null,"Candidat unique Colonne : "+String.valueOf(candidat));
                return;
            }
        }
        
        //Candidat unique dans la région ?
        for (int candidat=1;candidat<10;candidat++) {
            if (grille.getCaseEnCours().isCandidat(candidat) &&
                !grille.checkPresenceCandidatRegion(candidat, xSearch, ySearch)) {
                this.trouveDansDernierBalayage = true;
                grille.getCaseEnCours().setValeurCase(candidat);
                controle.demandeRefreshAffichageCase(xSearch, ySearch);
                grille.elimineCandidatsCaseTrouvee(xSearch, ySearch, candidat);
                controle.demandeAfficheCommande("Candidat unique région "+grille.getCaseEnCours().getRegion()+" : "+candidat);
                //javax.swing.JOptionPane.showMessageDialog(null,"Candidat unique Région : "+String.valueOf(candidat));
            }
        }
        
        //Recherche d'absence de candidats en colonne dans les autres régions.
        grille.traiteAbsenceCandidatColonneAutreRegion(xSearch, ySearch);
        
        //Recherche de triplette de candidat dans 3 cases en ligne : 
        grille.rechercheTripletteCandidatsLigne(xSearch, ySearch);
        
        
        // Recherche de paires de candidats en ligne
        if (grille.getCaseEnCours().getNombreCandidats()!=2 ) {return;}
        for (int indBalayage=0;indBalayage<9;indBalayage++) {
            if (indBalayage != xSearch &&
                grille.getCase(indBalayage, ySearch).nEstPasCaseInitiale() &&
                grille.getCase(indBalayage, ySearch).nEstPasCaseTrouvee() && 
                Arrays.equals(grille.getCaseEnCours().getCandidats(), grille.getCase(indBalayage, ySearch).getCandidats())) {
                javax.swing.JOptionPane.showMessageDialog(null,"Paire candidats en ligne "+String.valueOf(ySearch+1)+ " : "+
                                                          String.valueOf(grille.getCase(xSearch, ySearch).construitLibelleCandidats()));
                
                for (int x2=0;x2<9;x2++) {
                    if (grille.getCase(x2, ySearch).nEstPasCaseInitiale() &&
                        grille.getCase(x2, ySearch).nEstPasCaseTrouvee() && 
                        x2!=xSearch && x2 !=indBalayage) {
                        grille.getCase(x2, ySearch).elimineCandidats(grille.getCase(xSearch,ySearch).getCandidats());
                        controle.demandeRefreshAffichageCase(x2, ySearch);
                    }
                }
            }   
        }
        // Recherche de paires de candidats en colonne
        for (int indBalayage=0;indBalayage<9;indBalayage++) {
            if (indBalayage != ySearch &&
                grille.getCase(xSearch,indBalayage).nEstPasCaseInitiale() &&
                grille.getCase(xSearch,indBalayage).nEstPasCaseTrouvee() && 
                Arrays.equals(grille.getCaseEnCours().getCandidats(), grille.getCase(xSearch,indBalayage).getCandidats())) {
                javax.swing.JOptionPane.showMessageDialog(null,"Paire candidats en colonne "+String.valueOf(xSearch+1)+ " : "+
                                                          String.valueOf(grille.getCase(xSearch, ySearch).construitLibelleCandidats()));
                
                for (int y2=0;y2<9;y2++) {
                    if (grille.getCase(xSearch, y2).nEstPasCaseInitiale() &&
                        grille.getCase(xSearch, y2).nEstPasCaseTrouvee() && 
                        y2!=ySearch && y2 !=indBalayage) {
                        grille.getCase(xSearch, y2).elimineCandidats(grille.getCase(xSearch,ySearch).getCandidats());
                        controle.demandeRefreshAffichageCase(xSearch, y2);
                    }
                }
            }   
        }
        //Recherche de paire de candidats en région :
        grille.traitePaireCandidatsRegion(xSearch, ySearch);
        

        
        
    }

}
