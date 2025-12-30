package model.messagemanager;


import java.util.Map;


public class TemplateEngine {
    private TemplateEngine() throws InstantiationException {
        throw new InstantiationException("Utility class : "+this.getClass().getName());
    }

    public static String fillTemplate(String template, Map<String, String> variables) {
        String result = template;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }
}


