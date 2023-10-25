package control;

import model.Model;
import model.grille.Grille;
import utils.Utils;
import view.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {
	private final MyProperties myProperties = new MyProperties("config.properties");
	private final MyView myView;
    private final Model model;

	private final String initFileName;
	public MyView getVue() {return myView;}
    public static void main(String[] args) {new Control("");}
        
    public Control(String initFileName) {
		this.initFileName = initFileName;
		model = new Model(this);
    	
    	// Initialise la vue : 
    	myView = new MyView();
        myView.getBoutonAvance().addActionListener(this);
        myView.getBoutonExplique().addActionListener(this);
        myView.getBoutonRecule().addActionListener(this);
        myView.getMenuSave().addActionListener(this);
       
        this.refreshDisplayGrid(model.getGrille());
        myView.getFenetre().setVisible(true);
    }
    
    public void refreshDisplayGrid(Grille g) {
		myView.refreshGrilleDisplay(g);}
    
    public void refreshDisplayBox(int numCase) {
        if (model.getGrille().getCase(numCase).isCaseInitiale()) {
        	myView.setCaseInitiale(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase),
        			            String.valueOf(model.getGrille().getCase(numCase).getValeur()));
        	return;
        }
    	if (model.getGrille().getCase(numCase).isCaseTrouvee()) {
            myView.setCase(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase),
            		    String.valueOf(model.getGrille().getCase(numCase).getValeur()));
        }
        else {
            myView.setCaseCandidats(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase),
            		             model.getGrille().getCase(numCase).construitLibelleCandidats());
        }
    }

    public void insertDisplayMessage(String text) {
    	myView.getLogTextArea().insert(text+'\n', 0);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO : Externalize event manager from controller
		Object source = e.getSource();		
		if (source == myView.getBoutonAvance()) {
			model.detecteSuivant(false);
			return;
		}
		if (source == myView.getBoutonExplique()) {
			model.detecteSuivant(true);
			return;
		}
		if (source == myView.getBoutonRecule() && myView.getRangResolution().getText().equals("0")) {
			javax.swing.JOptionPane.showMessageDialog(null,myProperties.getProperty("InitialMessage"));
			return;
		}
		
		if (source == myView.getBoutonRecule()) {
			model.reloadLastHistoricization();
			this.refreshDisplayGrid(model.getGrille());
			this.decrementResolutionRank();
			myView.supprimeDernierLigneLog();
			return;
		}
		
		if (source == myView.getMenuSave()) {
			String fileName = myView.afficheSaveFileDialog();
			if (!fileName.isEmpty()) GridSaver.saveGrid(model.getGrille(),fileName);
		}
	}

	public void highlightCase(int numCase) {
		myView.setCaseAvantExplication(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase));
	}

    public void incrementResolutionRank() {
    	int temp = Integer.parseInt(myView.getRangResolution().getText());
    	temp+=1;
    	myView.getRangResolution().setText(String.valueOf(temp));
    }

    public void decrementResolutionRank() {
    	int temp = Integer.parseInt(myView.getRangResolution().getText());
    	temp-=1;
    	myView.getRangResolution().setText(String.valueOf(temp));
    }

	public String getInitFileName() {
		if (initFileName.isEmpty())
			return System.getProperty("user.dir")+ myProperties.getProperty("InitialFile");
		return initFileName;
	}

    public MyProperties getProperties() {return myProperties;}
}
