package model.messagemanager;

import model.grille.CaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resolution.CandidatUniqueDansZone;
import resolution.PaireConjugueeDansZone;
import resolution.ResolutionAction;

import java.util.HashMap;

@Service
public class MessageManager {
    private final TemplateProvider provider;

    @Autowired
    public MessageManager(TemplateProvider provider)  {
        this.provider = provider;
    }

    public String createMessageSolution(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String template = provider.getTemplate(action.getMethodeResolution().getSimpleName());

        HashMap<String, String> map = new HashMap<>();
        map.put("%solution", String.valueOf(action.getSolution()));
        map.put("%ligne", action.getContext().getYEdition());
        map.put("%colonne", action.getContext().getXEdition());
        map.put("%region",String.valueOf(action.getContext().getNumRegion()));

        if (action.getMethodeResolution() instanceof CandidatUniqueDansZone zoneMethod) {
            map.put("%zone", String.valueOf(zoneMethod.getZone()).toLowerCase());
        }
        return message+" "+TemplateEngine.fillTemplate(template, map);
    }
    public String createMessageElimination(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String template = provider.getTemplate(action.getMethodeResolution().getSimpleName());

        HashMap<String, String> map = new HashMap<>();
        map.put("%c1", String.valueOf(action.getCandidatUtilise(0)));
        map.put("%c2", String.valueOf(action.getCandidatUtilise(1)));
        if (action.getNombreCandidatsUtilises()>2) {
            map.put("%c3", String.valueOf(action.getCandidatUtilise(2)));
        }
        map.put("%candelim", String.valueOf(action.getCandidatAEliminer()));
        map.put("%ligne", action.getContext().getYEdition());
        map.put("%colonne", action.getContext().getXEdition());
        map.put("%region",String.valueOf(action.getContext().getNumRegion()));
        if (action.getMethodeResolution() instanceof PaireConjugueeDansZone zoneMethod) {
            map.put("%zone", String.valueOf(zoneMethod.getZone()).toLowerCase());
        }
        return message+" "+TemplateEngine.fillTemplate(template, map);
    }
    private String initializeMessage(CaseContext context) {
        String message ="";
        message+=String.format(provider.getTemplate("msgGeneral"), context.getXEdition(),
                context.getYEdition());
        return message;
    }
}
