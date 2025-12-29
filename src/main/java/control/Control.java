package control;

import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Control {
    private final Model model;

    public Model getModel() {return model;}
    public static void main(String[] args) {
        ApplicationInitializer.initialize();
	}

    @Autowired
	public Control( Model model) {
        this.model = model;
	}


}

