package control.command;

import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Explique")
public class ExpliqueCommand implements UiCommand {
    private final Model model;

    @Autowired
    public ExpliqueCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.detecteSuivant(true);
    }
}
