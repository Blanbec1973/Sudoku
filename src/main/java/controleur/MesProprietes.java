package controleur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MesProprietes {
    Properties prop = new Properties();
    private static final Logger logger = LogManager.getLogger(MesProprietes.class);

    public MesProprietes(String nomFichier) {
        try (InputStream input = new FileInputStream(nomFichier)) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            logger.fatal("Exception : {}",ex.getMessage());
        }
    }

    public String getFichierInitial() {
        return prop.getProperty("FichierInitial");
    }

}
