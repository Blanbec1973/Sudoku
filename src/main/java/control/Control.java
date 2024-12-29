package control;

import model.Model;
import model.ModelListener;
import view.ActingOnView;
import view.MyView;

public class Control {
	private final MyProperties myProperties = new MyProperties("config.properties");
	private final MyView myView;
    private final Model model;
	private final IEventManager eventManager;
	public MyView getVue() {return myView;}
	public Model getModel() {return model;}
	public MyProperties getMyProperties() {return myProperties;}
    public static void main(String[] args) {new Control();}
        
    public Control() {

    	myView = new MyView(new ActingOnView());
		eventManager = new EventManager(myView, myProperties);

		model = new Model((ModelListener) eventManager, myProperties);
		((EventManager) eventManager).setModel(model);

		myView.refreshGrilleDisplay(model.getGrille());
        myView.getFenetre().setVisible(true);
    }

	public void reloadGrille(String fileName) {
		((EventManager) eventManager).reloadGrille(fileName);
	}



}

