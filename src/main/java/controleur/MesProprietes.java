package controleur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MesProprietes {
    Properties prop = new Properties();
    private static final Logger LOGGER = Logger.getLogger(Controle.class.getPackage().getName());

    public MesProprietes(String nomFichier) {
        try (InputStream input = new FileInputStream(nomFichier)) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,"Exception : {0}",ex.getMessage());
        }
    }

    public String getFichierInitial() {
        return prop.getProperty("FichierInitial");
    }

}
