package control;

import model.Model;
import model.grille.Grille;
import utils.Utils;
import view.MyView;

public class Control  {
	private final MyProperties myProperties = new MyProperties("config.properties");
	private final MyView myView;
    private final Model model;
	private final EventManager eventManager;
	private final String initFileName;
	public MyView getVue() {return myView;}
	public Model getModel() {return model;}
    public static void main(String[] args) {new Control("");}
        
    public Control(String initFileName) {
		this.initFileName = initFileName;
		model = new Model(this);
    	
    	// Initialize view and eventManager :
    	myView = new MyView();
		eventManager = new EventManager(this, myView);
        myView.getBoutonAvance().addActionListener(eventManager);
        myView.getBoutonExplique().addActionListener(eventManager);
        myView.getBoutonRecule().addActionListener(eventManager);
        myView.getMenuSave().addActionListener(eventManager);
       
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
}
