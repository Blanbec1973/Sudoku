package control;

import control.eventmanager.EventManager;
import control.eventmanager.ModelToViewSynchonizer;
import model.Model;
import model.SimpleModelEventPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.MyView;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationInitializer {

    private ApplicationInitializer() throws InstantiationException {
        throw new InstantiationException("Utility class ; "+this.getClass().getName());
    }

    public static void initialize() {
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

        Path path = Paths.get(System.getProperty("user.dir") + myProperties.getProperty("InitialFile")).toAbsolutePath();
        model.reload(path);
        myView.refreshGrilleDisplay(model.getGrille());
        myView.getFenetre().setVisible(true);
    }


}
