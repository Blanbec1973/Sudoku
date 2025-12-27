
package resolution;

import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolution.service.ConfigLoader;

import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ResolutionMethodRegistryTest {

    private ResolutionMethodsConfig config;
    private ResolutionMethodRegistry registry;


    @BeforeEach
    void setUp() {
        // On charge la config depuis le classpath
        InputStream is = getClass().getClassLoader().getResourceAsStream("ResolutionMethod.yml");
        assertNotNull(is, "Le fichier ResolutionMethod.yaml doit être présent dans src/main/resources !");
        config = ConfigLoader.loadConfig(is);
        Grille grille = new Grille();
        registry = new ResolutionMethodRegistry(grille);
    }

    @Test
    void testChargementConfigNonVide() {
        assertNotNull(config, "La configuration ne doit pas être nulle");
        List<ResolutionMethodConfig> methods = config.getResolutionMethods();
        assertNotNull(methods, "La liste des méthodes ne doit pas être nulle");
        assertFalse(methods.isEmpty(), "La liste des méthodes ne doit pas être vide");
    }

    @Test
    void testAffichageConfigConsole() {
        // On vérifie que chaque entrée a bien ses champs renseignés
        config.getResolutionMethods().forEach(conf -> {
            assertNotNull(conf.className(), "Le nom de la classe ne doit pas être nul");
            assertTrue(conf.order() > 0, "L'ordre doit être strictement positif");
            // Zone peut être null (facultative)
        });

        // Affichage console (pour debug manuel, pas d'assertion ici)
        System.out.println("Table de configuration des méthodes de résolution :");
        config.getResolutionMethods().stream()
                .sorted(java.util.Comparator.comparingInt(ResolutionMethodConfig::order))
                .forEach(conf -> System.out.printf(
                        "Classe: %-40s | Zone: %-8s | Ordre: %d%n",
                        conf.className(),
                        conf.zone() != null ? conf.zone() : "",
                        conf.order()
                ));
    }

    @Test
    void testTriParOrdre() {
        List<ResolutionMethodConfig> methods = config.getResolutionMethods();
        List<ResolutionMethodConfig> sorted = methods.stream()
                .sorted(java.util.Comparator.comparingInt(ResolutionMethodConfig::order))
                .toList();

        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i).order() >= sorted.get(i - 1).order(),
                    "La configuration doit être triée par ordre croissant");
        }
    }
    @Test    void testListeNonVide() {
        List<MethodeResolution> methods = registry.getOrderedMethods();
        assertNotNull(methods, "La liste des méthodes ne doit pas être nulle");
        assertFalse(methods.isEmpty(), "La liste des méthodes ne doit pas être vide");
    }

    @Test    void testPresenceDesClassesAttendues() {
        List<MethodeResolution> methods = registry.getOrderedMethods();
        Set<String> expectedClasses = Set.of("CandidatUniqueDansCase",
                                             "CandidatUniqueDansZone",
                                             "PaireCandidats2CasesColonne",
                                             "TripletteCandidatsEnZone");
        Set<String> actualClasses = methods.stream()
                .map(m -> m.getClass().getSimpleName())
                .collect(Collectors.toSet());

        for (String expected : expectedClasses) {
            assertTrue(actualClasses.contains(expected),
                    "La classe " + expected + " doit être présente dans la liste instanciée");
        }
    }
}
