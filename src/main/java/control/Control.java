package control;

import model.MessageManager;
import model.Model;
import model.SimpleModelEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import view.MyView;
import view.ViewUpdater;

import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Control {
    private final Model model;
	private final EventManager eventManager;
	public Model getModel() {return model;}
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyProperties myProperties = context.getBean(MyProperties.class);
        //MyProperties myProperties = new MyProperties("config.properties");
		MyView myView= context.getBean(MyView.class);
        //MyView myView = new MyView(myProperties.getProperty("InitialDirectory"));

		//services :
		MessageManager messageManager = context.getBean(MessageManager.class);
        //MessageManager messageManager = new MessageManager(myProperties);
		//HistorisationService historisationService = new HistorisationService();
		SimpleModelEventPublisher publisher = context.getBean(SimpleModelEventPublisher.class);
        //SimpleModelEventPublisher publisher = new SimpleModelEventPublisher();
		//ModelEventService eventService = new ModelEventService(publisher);
		//ResolutionMessageService messageService = new ResolutionMessageService(messageManager);

		// Model
		Model model = context.getBean(Model.class);
        //Model model = new Model(eventService, messageService, historisationService);

		// EventManager
		EventManager eventManager = context.getBean(EventManager.class);
        //EventManager eventManager = new EventManager(myView, myProperties);
		publisher.addListener(eventManager);
		eventManager.setModel(model);
		myView.registerController(eventManager);

		// Control with injection
		Control control = context.getBean(Control.class);
		control.initialize(myView, myProperties); // méthode pour charger la grille initial

		myView.getFenetre().setVisible(true);
	}

    @Autowired
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

