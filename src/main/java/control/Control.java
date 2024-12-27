package control;

import model.Model;
import model.grille.Grille;
import utils.Utils;
import view.MyView;

public class Control  {
	private final MyProperties myProperties = new MyProperties("config.properties");
	private final MyView myView;
    private final Model model;
	private final String initFileName;
	public MyView getVue() {return myView;}
	public Model getModel() {return model;}
    public static void main(String[] args) {new Control("");}
        
    public Control(String initFileName) {
		this.initFileName = initFileName;
		model = new Model(this);
    	
    	// Initialize view and eventManager :
    	myView = new MyView();
		EventManager eventManager = new EventManager(this, myView);
        myView.getBoutonAvance().addActionListener(eventManager);
        myView.getBoutonExplique().addActionListener(eventManager);
        myView.getBoutonRecule().addActionListener(eventManager);
        myView.getMenuSave().addActionListener(eventManager);
		myView.getMenuOpen().addActionListener(eventManager);
       
        this.refreshDisplayGrid(model.getGrille());
        myView.getFenetre().setVisible(true);
    }
    
    public void refreshDisplayGrid(Grille g) {
		myView.refreshGrilleDisplay(g);}
    
    public void refreshDisplayBox(int numCase) {
        if (model.getGrille().isCaseInitiale(numCase)) {
        	myView.setCaseInitiale(numCase, String.valueOf(model.getGrille().getValeurCase(numCase)));
        	return;
        }
    	if (model.getGrille().isCaseTrouvee(numCase)) {
            myView.setCase(numCase, String.valueOf(model.getGrille().getValeurCase(numCase)));
        }
        else {
            myView.setCaseCandidats(numCase, model.getGrille().construitLibelleCandidats(numCase));
        }
    }

    public void insertDisplayMessage(String text) {
		myView.getLogTextArea().insert(text+'\n', 0);
		myView.getLogTextArea().setCaretPosition(0);
		myView.getPanCommande().revalidate();
		myView.getPanCommande().repaint();
    }


	public void highlightCase(int numCase) {
		myView.setCaseAvantExplication(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase));
	}

    public void incrementResolutionRank() {
    	int temp = Integer.parseInt(myView.getRangResolution().getText());
    	temp+=1;
    	myView.getRangResolution().setText(String.valueOf(temp));
    }

	public String getInitFileName() {
		if (initFileName.isEmpty())
			return System.getProperty("user.dir")+ myProperties.getProperty("InitialFile");
		return initFileName;
	}

    public MyProperties getProperties() {return myProperties;}

	void reloadGrille(String fileName2) {
		model.reload(fileName2);
		this.refreshDisplayGrid(model.getGrille());
		myView.getRangResolution().setText("0");
		myView.getLogTextArea().setText(myProperties.getProperty("StartMessage"));
	}
}

