package control.command;

import control.eventmanager.ResolutionService;
import org.springframework.stereotype.Component;

@Component("resolution")
public class ResolutionCommand implements UiCommand {
    private final ResolutionService resolutionService;

    public ResolutionCommand(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @Override
    public void execute() {
        resolutionService.resolution();
    }
}
