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
import java.nio.file.Path;
import java.nio.file.Paths;


public class Control {
    private final Model model;
	private final EventManager eventManager;
	public Model getModel() {return model;}
    public static void main(String[] args) {
		MyProperties myProperties = new MyProperties("config.properties");
		MyView myView = new MyView();

		//services :
		MessageManager messageManager = new MessageManager(myProperties);
		HistorisationService historisationService = new HistorisationService();
		SimpleModelEventPublisher publisher = new SimpleModelEventPublisher();
		ModelEventService eventService = new ModelEventService(publisher);
		ResolutionMessageService messageService = new ResolutionMessageService(messageManager);

		// Modèle
		Model model = new Model(eventService, messageService, historisationService);

		// EventManager
		EventManager eventManager = new EventManager(myView, myProperties);
		publisher.addListener(eventManager);
		eventManager.setModel(model);
		myView.registerController(eventManager);

		// Control avec injection
		Control control = new Control(eventManager, model);
		control.initialize(myView, myProperties); // méthode pour charger la grille initiale

		myView.getFenetre().setVisible(true);
	}
	public Control(EventManager eventManager, Model model) {
		this.eventManager = eventManager;
		this.model = model;
	}

	public void initialize(ViewUpdater view, MyProperties properties) {
		Path path = Paths.get(System.getProperty("user.dir") + properties.getProperty("InitialFile")).toAbsolutePath();
		model.reload(path);
		view.refreshGrilleDisplay(model.getGrille());
	}
	public void reloadGrille(Path path) {
		eventManager.reloadGrille(path);
	}
	public void simulateClick(String command) {
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command);
		eventManager.actionPerformed(event);
	}
	public ViewUpdater getViewUpdater() {
		return eventManager;
	}

}

