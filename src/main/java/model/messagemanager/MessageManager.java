package model.messagemanager;

import model.grille.CaseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resolution.MethodeResolution;
import resolution.ResolutionAction;

import java.lang.reflect.Method;
import java.util.HashMap;

@Service
public class MessageManager {
    private final TemplateProvider provider;
    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class);

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

        String zone = hasGetZoneMethod(action.getMethodeResolution());
        if (zone != null) {
            map.put("%zone", zone);
        }
        return message+" "+TemplateEngine.fillTemplate(template, map);
    }
    public String createMessageElimination(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String template = provider.getTemplate(action.getMethodeResolution().getSimpleName());
        if (logger.isDebugEnabled()) logger.debug(action.toString());

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
        String zone = hasGetZoneMethod(action.getMethodeResolution());
        if (zone != null) {
            map.put("%zone", zone);
        }
        return message+" "+TemplateEngine.fillTemplate(template, map);
    }
    private String initializeMessage(CaseContext context) {
        String message ="";
        message+=String.format(provider.getTemplate("msgGeneral"), context.getXEdition(),
                context.getYEdition());
        return message;
    }

    private String hasGetZoneMethod(MethodeResolution method) {
        try {
            Method getZoneMethod = method.getClass().getMethod("getZone");
            Object zoneValue = getZoneMethod.invoke(method);
            if (zoneValue != null) {
                return zoneValue.toString().toLowerCase();
            }
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Exception e) {
            logger.error("Error while checking getZone() method : {}", e.getMessage());
            throw  new RuntimeException("Error while checking getZone() method.", e);
        }
        return null;
    }
}
