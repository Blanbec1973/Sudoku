package control.eventmanager;

import control.command.UiCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

@Service
public class EventManager implements ActionListener {
    private final Map<String, UiCommand> commandMap;

    @Autowired
    public EventManager(Map<String, UiCommand> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UiCommand cmd = commandMap.get(e.getActionCommand());
        if (cmd != null) cmd.execute();
        else throw new IllegalArgumentException("Commande inconnue !");
    }

    public void simulateClick(String command) {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command);
        this.actionPerformed(event);
    }

}
