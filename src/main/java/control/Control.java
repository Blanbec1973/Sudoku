package control;

import model.Model;
import model.SimpleModelEventPublisher;
import view.MyView;
import view.ViewUpdater;

import java.awt.event.ActionEvent;


public class Control {
	private final MyProperties myProperties = new MyProperties("config.properties");
	private final ViewUpdater viewUpdater; // Abstraction pour la vue
    private final Model model;
	private final EventManager eventManager;
	public Model getModel() {return model;}
	public MyProperties getMyProperties() {return myProperties;}
    public static void main(String[] args) {new Control();}
        
    public Control() {

    	MyView myView = new MyView();
		this.viewUpdater = myView;
		eventManager = new EventManager(myView, myProperties);
		myView.registerController(eventManager);

		SimpleModelEventPublisher publisher = new SimpleModelEventPublisher();
		publisher.addListener(eventManager);

		model = new Model(publisher, myProperties);
		eventManager.setModel(model);

		myView.refreshGrilleDisplay(model.getGrille());
        myView.getFenetre().setVisible(true);
    }

	public void reloadGrille(String fileName) {
		eventManager.reloadGrille(fileName);
	}

	public void simulateClick(String command) {
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command);
		eventManager.actionPerformed(event);
	}
	public ViewUpdater getViewUpdater() {
		return viewUpdater;
	}

}

