package control;

import control.eventmanager.EventManager;
import control.eventmanager.ModelToViewSynchonizer;
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
		MyView myView= context.getBean(MyView.class);

		//services :
        SimpleModelEventPublisher publisher = context.getBean(SimpleModelEventPublisher.class);

		// Model
		Model model = context.getBean(Model.class);

		// EventManager
		EventManager eventManager = context.getBean(EventManager.class);
        ModelToViewSynchonizer synchonizer = context.getBean(ModelToViewSynchonizer.class);
		publisher.addListener(synchonizer);
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
//	public ViewUpdater getViewUpdater() {
//		return eventManager;
//	}

}

