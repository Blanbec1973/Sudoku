package resolution;

import model.grille.Grille;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import resolution.service.ConfigLoader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResolutionMethodRegistry {

    private final List<MethodeResolution> orderedMethods;
    private final Grille grille;
    static final Logger logger = LoggerFactory.getLogger(ResolutionMethodRegistry.class);

    public static void main(String[] args) {
        new ResolutionMethodRegistry(null);
    }

    public ResolutionMethodRegistry(Grille grille) {
        this.grille=grille;
        InputStream is = getClass().getClassLoader().getResourceAsStream("ResolutionMethod.yml");
        if (is == null) {
            throw new RuntimeException("File ResolutionMethod.yaml not found in classpath !");
        }
        ResolutionMethodsConfig config = ConfigLoader.loadConfig(is);

        config.getResolutionMethods().stream()
                .sorted(Comparator.comparingInt(ResolutionMethodConfig::order))
                .forEach(conf -> logger.info(
                        String.format("Class: %-60s | Zone: %-8s | Order: %-2d |",
                        conf.className(),
                        conf.zone() != null ? conf.zone() : "",
                        conf.order()
                )));

        orderedMethods = config.getResolutionMethods().stream()
             .map(this::instantiateResolutionMethod)
             .collect(Collectors.toList());
    }

    private MethodeResolution instantiateResolutionMethod(ResolutionMethodConfig conf) {
        String className = conf.className();
        ZoneType zone = conf.zone();
        Class<?> clazz;
        logger.info("Instantiation {} with zone {}", className, zone);
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            if (zone == null) {
                Constructor<?> ctor = clazz.getConstructor(Grille.class);
                return (MethodeResolution) ctor.newInstance(grille);
            } else {
                Constructor<?> ctor = clazz.getConstructor(Grille.class, ZoneType.class);
                return (MethodeResolution) ctor.newInstance(grille, zone);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MethodeResolution> getOrderedMethods() {
        return orderedMethods;
    }
}
