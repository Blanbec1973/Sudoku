package control.command;

import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Avance")
public class AvanceCommand implements UiCommand {
    private final Model model;

    @Autowired
    public AvanceCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.detecteSuivant(false);
    }
}
