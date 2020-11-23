package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grille {
    private Case [][] mesCases = new Case [9][9];
    private int xSearch, ySearch, xRegion, yRegion;
    private Modele modele;
    private ArrayList<Integer> casesAtrouver;
        
    public Grille(Modele modele) {
        this.modele = modele;
        int numcase = 0;
        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                numcase = numcase +1;
                mesCases[x][y] = new Case(numcase, x, y);
            }
        }
        casesAtrouver = new ArrayList<Integer>();
    }
     
    public Case getCase(int x, int y) {return mesCases[x][y];}
    public Case getCaseEnCours() {return mesCases[xSearch][ySearch];}
    public int getxSearch() {return xSearch;}
    public int getySearch() {return ySearch;}
    public ArrayList<Integer> getCasesAtrouver() {return casesAtrouver;}
    
    public void setValeurCaseEnCours(int solution) {
        mesCases[xSearch][ySearch].setValeurCase(solution);
        modele.getControle().demandeRefreshAffichageCase(xSearch, ySearch);
        this.elimineCandidatsCaseTrouvee(xSearch, ySearch, solution);
    }

	public void setCaseEnCours(int numCase) {
		this.calculXYSearchEtRegion(numCase);
	}
    
    public void calculXYSearchEtRegion(int numCase) {
        //Calcule xSearch et ySearch à partir de numCase
        xSearch = Utils.calculXsearch(numCase);
        ySearch = Utils.calculYsearch(numCase);
        
        // Calcul de la région : 
        
        switch (xSearch) {
            case 0,1,2 -> xRegion=0;
            case 3,4,5 -> xRegion=3;
            case 6,7,8 -> xRegion=6;        
        }
        switch (ySearch) {
            case 0,1,2 -> yRegion=0;
            case 3,4,5 -> yRegion=3;
            case 6,7,8 -> yRegion=6;
        }
    }
        

    public void init (String nomFichier)  {
        String readLine;
        int valeur;
        int indexCase = 1;
        File monFichier = new File(nomFichier);
        int y=0;
        try {
            BufferedReader b = new BufferedReader(new FileReader(monFichier));
            while ((readLine = b.readLine()) != null) {
                //System.out.println("Ligne :"+y+" : "+ readLine);
                for (int x=0;x<9;x++) {
                    //System.out.println(readLine.substring(x,x+1));
                    valeur = Integer.parseInt(readLine.substring(x,x+1));
                    //System.out.println("xy="+x+y);
                    if (valeur != 0) {
                        mesCases[x][y].setValeurCase(valeur);
                        mesCases[x][y].setCaseInitiale();
                    }
                    else {
                    	casesAtrouver.add(indexCase);
                    }
                    indexCase+=1;
                }
                y++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Display() {
        //System.out.println(" Méthode Display : ");
        String ligne = "";
        for (int y=0; y<9; y++) {
            // Préparation de la ligne à imprimer :
            for (int x=0;x<9;x++) {
                ligne = ligne + this.getCase(x,y).getValeur();   
            }
            System.out.println(ligne);
            ligne ="";
        }
    }
    
    public void calculCandidatsInitiaux(int x, int y) {
        for (int valeur=1;valeur<10;valeur++) {
            if (this.checkPresenceValeurLigne(valeur, y)) {mesCases[x][y].elimineCandidat(valeur);}
            if (this.checkPresenceValeurColonne(valeur, x)) {mesCases[x][y].elimineCandidat(valeur);}
            if (this.checkPresenceValeurRegion(valeur, x, y)) {mesCases[x][y].elimineCandidat(valeur);}
        }
    }
    
    public boolean checkPresenceValeurLigne(int valeur, int numLigne) {
        for (int i=0;i<9;i++) {
            if (this.mesCases[i][numLigne].getValeur()==valeur) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceValeurColonne(int valeur, int numColonne) {
        for (int i=0;i<9;i++) {
            if (this.mesCases[numColonne][i].getValeur()==valeur) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceValeurRegion(int valeur, int xSearch, int ySearch) {
        for (int x=xRegion;x<xRegion+3;x++) {
            for (int y=yRegion;y<yRegion+3;y++) {
                if (this.mesCases[x][y].getValeur() == valeur) {return true;}
            }
        }
        return false;
    }
    
    public boolean checkPresenceCandidatLigne(int valeur, int x, int numLigne) {
        for (int i=0;i<9;i++) {
            if (mesCases[i][numLigne].nEstPasCaseInitiale() && 
                mesCases[i][numLigne].nEstPasCaseTrouvee() &&
                i!=x && this.mesCases[i][numLigne].isCandidat(valeur)) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceCandidatColonne(int valeur, int numcol, int y) {
        for (int i=0;i<9;i++) {
            if (mesCases[numcol][i].nEstPasCaseInitiale() && 
                mesCases[numcol][i].nEstPasCaseTrouvee() &&
                i!=y && this.mesCases[numcol][i].isCandidat(valeur)) {return true;}
        }
        return false;
    }

    public boolean checkPresenceCandidatRegion(int indiceCandidat, int x, int y) { 
        for (int abs=xRegion;abs<xRegion+3;abs++) {
            for (int ord=yRegion;ord<yRegion+3;ord++) {
                if (mesCases[abs][ord].nEstPasCaseInitiale() && 
                    mesCases[abs][ord].nEstPasCaseTrouvee() &&
                    (x!=abs || y!=ord) && 
                    this.mesCases[abs][ord].isCandidat(indiceCandidat)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void elimineCandidatsCaseTrouvee(int x, int y, int solution) {
        //Case x, y trouvée ==> élimination du candidats dans ligne/colonne/région.
        //Elimination dans ligne : 
        for (int i=0;i<9;i++) {
            mesCases[i][y].elimineCandidat(solution);
            if (mesCases[i][y].nEstPasCaseInitiale() && mesCases[i][y].nEstPasCaseTrouvee()) {
            	modele.getControle().demandeRefreshAffichageCase(i, y);
            }
        }
        
        //Elimination dans colonne : 
        for (int i=0;i<9;i++) {
            mesCases[x][i].elimineCandidat(solution);
            if (mesCases[x][i].nEstPasCaseInitiale() && mesCases[x][i].nEstPasCaseTrouvee()) {
            	modele.getControle().demandeRefreshAffichageCase(x, i);
            }
        }
        
        //Elimination dans région : 
        for (int abs=xRegion;abs<xRegion+3;abs++) {
            for (int ord=yRegion;ord<yRegion+3;ord++) {
                mesCases[abs][ord].elimineCandidat(solution);
                if (mesCases[abs][ord].nEstPasCaseInitiale() && mesCases[abs][ord].nEstPasCaseTrouvee()) {
                	modele.getControle().demandeRefreshAffichageCase(abs, ord);
                }
            }
        }
    }

	public boolean CheckPresenceCandidatRegionSaufColonne(int candidat, int colonne) {
        for (int abs=xRegion;abs<xRegion+3;abs++) {
            for (int ord=yRegion;ord<yRegion+3;ord++) {
                if (abs!= colonne && mesCases[abs][ord].nEstPasCaseInitiale() && 
                		             mesCases[abs][ord].nEstPasCaseTrouvee()) {
                    if (mesCases[abs][ord].isCandidat(candidat)) return true;
                    
                }     
            }
        }  
		return false;
	}
    
    public void elimineCandidatRegionSaufColonne(int candidat, int colonne) {
        //Elimination dans région : 
        for (int abs=xRegion;abs<xRegion+3;abs++) {
            for (int ord=yRegion;ord<yRegion+3;ord++) {
                if (abs!= colonne && mesCases[abs][ord].nEstPasCaseInitiale() && mesCases[abs][ord].nEstPasCaseTrouvee()) {
                    mesCases[abs][ord].elimineCandidat(candidat);
                    modele.getControle().demandeRefreshAffichageCase(abs, ord);
                }     
            }
        }   
    }
    
    void elimineCandidatsLigneSaufColonnes(Candidats candidats, int numligne, int colonnesANePasPrendreEnCompte []) {
        for (int i=0;i<9;i++) {
            if (this.getCase(i, numligne).nEstPasCaseInitiale() &&
                this.getCase(i, numligne).nEstPasCaseTrouvee() &&
                !Arrays.toString(colonnesANePasPrendreEnCompte).contains(String.valueOf(i))) {
                this.getCase(i,numligne).elimineCandidats(candidats.getCandidats());
                modele.getControle().demandeRefreshAffichageCase(i,numligne);
            }   
        }
    }
    
    
    void traitePaireCandidatsRegion(int xSearch, int ySearch) {
        //Détection de paire de candidats : 
        boolean paireCandidatsTrouvee = false;
        for (int abs=xRegion;abs<xRegion+3;abs++) {
            for (int ord=yRegion;ord<yRegion+3;ord++) {
                if ((xSearch != abs || ySearch != ord) &&
                    this.getCase(abs, ord).nEstPasCaseInitiale() &&
                    this.getCase(abs, ord).nEstPasCaseTrouvee() &&
                    Arrays.equals(this.getCase(xSearch, ySearch).getCandidats(),this.getCase(abs, ord).getCandidats())) {
                    paireCandidatsTrouvee = true;
                     javax.swing.JOptionPane.showMessageDialog(null,"Paire candidats en région "+" : "+
                                                          String.valueOf(this.getCase(xSearch, ySearch).construitLibelleCandidats()));
                }
            }
        }
        if (!paireCandidatsTrouvee) {return;}
        for (int abs=xRegion;abs<xRegion+3;abs++) {
            for (int ord=yRegion;ord<yRegion+3;ord++) {
                if (this.getCase(abs, ord).nEstPasCaseInitiale() &&
                    this.getCase(abs, ord).nEstPasCaseTrouvee() &&
                    !Arrays.equals(this.getCase(xSearch, ySearch).getCandidats(),this.getCase(abs, ord).getCandidats())) {
                    this.getCase(abs, ord).elimineCandidats(this.getCase(xSearch, ySearch).getCandidats());
                    modele.getControle().demandeRefreshAffichageCase(abs, ord);
                }
            }
        }        
        
    }

    void traiteAbsenceCandidatColonneAutreRegion(int xSearch, int ySearch) {
        boolean candidatTrouve;
        
        for (int candidat=1;candidat<10;candidat++) {
            if (this.getCaseEnCours().isCandidat(candidat)) {
                candidatTrouve=false;
                for (int i=0;i<9;i++) {
                    if (this.getCase(xSearch, ySearch).getRegion() != this.getCase(xSearch,i).getRegion() &&
                        this.getCase(xSearch, i).nEstPasCaseInitiale() &&
                        this.getCase(xSearch, i).nEstPasCaseTrouvee() &&
                        this.getCase(xSearch,i).isCandidat(candidat)) {
                        candidatTrouve = true;
                    }
                }    
                if (!candidatTrouve) {
                    javax.swing.JOptionPane.showMessageDialog(null,"Candidat "
                                                                   +String.valueOf(candidat)
                                                                   + " dans colonne "
                                                                   +String.valueOf(xSearch+1)
                                                                   +"uniquement dans région"
                                                                   +String.valueOf(this.getCaseEnCours().getRegion())); 
                    this.elimineCandidatRegionSaufColonne(candidat, xSearch);
                }        
            }    
        }
    }

    void rechercheTripletteCandidatsLigne(int xSearch, int ySearch) {
        int nombreCaseATrouver = Utils.calculNombreCaseATrouverDansLigne(this, ySearch);
        int x2, x3;
        x2=0;x3=0;
        //Vérification qu'il y a au moins 4 cases à trouver : 
        if (nombreCaseATrouver<=3) {
            //System.out.println("Ligne "+String.valueOf(ySearch+1)+" ==> Pas de recherche de triplettes.");
            return;
        }
        //System.out.println("Ligne "+String.valueOf(ySearch+1)+" ==> Recherche de triplettes depuis colonne "+String.valueOf(xSearch+1));
        while (x2<9) {
            if (x2!=xSearch && this.getCase(x2,ySearch).nEstPasCaseInitiale() && this.getCase(x2, ySearch).nEstPasCaseTrouvee()) {
                x3=x2;
                while (x3<9) {
                    if (x3!=x2 && x3!=xSearch && this.getCase(x3,ySearch).nEstPasCaseInitiale() 
                               && this.getCase(x3, ySearch).nEstPasCaseTrouvee()) {
                        //System.out.println("Ligne "+String.valueOf(ySearch+1)
                        //                           +" triplette : "
                        //                           +String.valueOf(xSearch+1)
                        //                           +String.valueOf(x2+1)
                        //                           +String.valueOf(x3+1));
                        this.traiteTripletteEnLigne(xSearch, ySearch, x2, x3);
                    }
                    x3+=1;
                }
            }
            x2+=1;
        }
        
        
    }

    private void traiteTripletteEnLigne(int xSearch, int ySearch, int x2, int x3) {
        Candidats testCandidats = new Candidats(this.getCaseEnCours().getCandidats());
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(this.getCaseEnCours().getCandidats(),
                                                       this.getCase(x2, ySearch).getCandidats()));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       this.getCase(x3, ySearch).getCandidats()));
        if (testCandidats.getNombreCandidats()>3) { return;}
        
        javax.swing.JOptionPane.showMessageDialog(null,"Triplette trouvée en ligne "+String.valueOf(ySearch+1)+ ", colonnes : "
                                                   +String.valueOf(xSearch+1)
                                                   +String.valueOf(x2+1)
                                                   +String.valueOf(x3+1));
        int  tableau[]=new int [3];
        tableau[0]=xSearch;tableau[1]=x2;tableau[2]=x3;       
        this.elimineCandidatsLigneSaufColonnes(testCandidats, ySearch, tableau);
    }







}
