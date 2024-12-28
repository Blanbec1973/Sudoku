package control;

import model.Model;
import model.ModelListener;
import view.MyView;

public class Control {
	private final MyProperties myProperties = new MyProperties("config.properties");
	private final MyView myView;
    private final Model model;
	public MyView getVue() {return myView;}
	public Model getModel() {return model;}
	public MyProperties getMyProperties() {return myProperties;}
    public static void main(String[] args) {new Control();}
        
    public Control() {

    	myView = new MyView();
		IEventManager eventManager = new EventManager(this, myView);

		model = new Model((ModelListener) eventManager, myProperties);

		myView.refreshGrilleDisplay(model.getGrille());
        myView.getFenetre().setVisible(true);
    }

	public void reloadGrille(String fileName2) {
		model.reload(fileName2);
		myView.refreshGrilleDisplay(model.getGrille());
		myView.getRangResolution().setText("0");
		myView.getLogTextArea().setText(myProperties.getProperty("StartMessage"));
	}


}

