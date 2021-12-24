package modele;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MesProprietes {
    Properties prop = new Properties();

    public MesProprietes() {
        try (InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties")) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getFichierInitial() {
        return prop.getProperty("FichierInitial");
    }

}
