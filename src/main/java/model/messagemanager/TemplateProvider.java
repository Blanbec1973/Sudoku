package model.messagemanager;

import control.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateProvider {
    private final MyProperties prop;

    @Autowired
    public TemplateProvider(MyProperties prop)  {
        this.prop = prop;
    }

    protected String getTemplate(String keyTemplate) {
        return prop.getProperty(keyTemplate);
    }


}
