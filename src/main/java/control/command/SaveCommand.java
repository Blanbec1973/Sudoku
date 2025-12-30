package control.command;

import control.eventmanager.GridSaverService;
import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.MyView;
import view.ViewUpdater;

@Component("Save")
public class SaveCommand implements UiCommand {
    private final ViewUpdater viewUpdater;
    private final Model model;
    private final GridSaverService saverService;

    @Autowired
    public SaveCommand(ViewUpdater viewUpdater, Model model, GridSaverService saverService) {
        this.viewUpdater = viewUpdater;
        this.model = model;
        this.saverService = saverService;
    }

    @Override
    public void execute() {
        String fileName = ((MyView) viewUpdater).afficheSaveFileDialog("Save");
        if (!fileName.isEmpty()) saverService.saveGrid(model.getGrille(), fileName);
    }
}
