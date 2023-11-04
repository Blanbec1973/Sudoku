package control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {
    final Properties prop = new Properties();
    private static final Logger logger = LogManager.getLogger(MyProperties.class);

    public MyProperties(String nomFichier) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(nomFichier)) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            logger.fatal("Exception : {}",ex.getMessage());
        }
    }

    public String getFichierInitial() {
        return prop.getProperty("FichierInitial");
    }

    public String getProperty(String keyProperty) { return prop.getProperty(keyProperty);}

}
