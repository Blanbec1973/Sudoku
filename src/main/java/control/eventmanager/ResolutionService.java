package control.eventmanager;

import model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class ResolutionService {
    private static final Logger logger = LoggerFactory.getLogger(ResolutionService.class);
    private final Model model;

    @Autowired
    public ResolutionService(Model model) {
        this.model = model;
    }

    void resolution() {
        logger.info("Demande de résolution globale.");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!model.detecteSuivant(true))
                    timer.cancel();
            }
        }, 0, 200); // Démarre immédiatement, répète toutes les secondes

    }
}
