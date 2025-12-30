package control.command;

import control.eventmanager.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.MyView;
import view.ViewUpdater;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component("open")
public class OpenCommand implements UiCommand {
    private final ViewUpdater viewUpdater;
    private final NavigationService navigationService;

    @Autowired
    public OpenCommand(ViewUpdater viewUpdater, NavigationService navigationService) {
        this.viewUpdater = viewUpdater;
        this.navigationService = navigationService;
    }

    @Override
    public void execute() {
        String fileName2 = ((MyView) viewUpdater).afficheSaveFileDialog("open");
        Path path = Paths.get(fileName2).toAbsolutePath();
        if (!fileName2.isEmpty()) navigationService.reloadGrille(path);
    }
}
