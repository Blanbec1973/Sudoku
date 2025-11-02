package control;

import model.MessageManager;
import model.Model;
import model.SimpleModelEventPublisher;
import model.service.HistorisationService;
import model.service.ModelEventService;
import model.service.ResolutionMessageService;
import view.MyView;
import view.ViewUpdater;

import java.awt.event.ActionEvent;


public class Control {
	private final MyProperties myProperties = new MyProperties("config.properties");
    private final Model model;
	private final EventManager eventManager;
	public Model getModel() {return model;}
	public MyProperties getMyProperties() {return myProperties;}
    public static void main(String[] args) {new Control();}
        
    public Control() {

    	MyView myView = new MyView();
		eventManager = new EventManager(myView, myProperties);
		myView.registerController(eventManager);

		SimpleModelEventPublisher publisher = new SimpleModelEventPublisher();
		publisher.addListener(eventManager);

		MessageManager messageManager = new MessageManager(myProperties);

		model = new Model(new ModelEventService(publisher),
				          new ResolutionMessageService(messageManager), new HistorisationService());
		String initialFile = System.getProperty("user.dir") + myProperties.getProperty("InitialFile");
		model.reload(initialFile);
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
		return eventManager;
	}

}

