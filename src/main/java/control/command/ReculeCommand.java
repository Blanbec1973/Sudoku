package control.command;

import control.eventmanager.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("recule")
public class ReculeCommand implements UiCommand {
    private final NavigationService navigationService;

    @Autowired
    public ReculeCommand(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @Override
    public void execute() {
        navigationService.handleRecule();
    }
}
