package control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class MyProperties {
    final Properties prop = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(MyProperties.class);

    public MyProperties(@Value("$(config.file)") String nomFichier) {
        logger.info("File to open : {}", nomFichier);
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(nomFichier)) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            logger.error("Exception : {}",ex.getMessage());
        }
    }

    public String getProperty(String keyProperty) { return prop.getProperty(keyProperty);}

}
