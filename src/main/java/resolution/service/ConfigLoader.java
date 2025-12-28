package resolution.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import exceptions.ConfigLoaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resolution.ResolutionMethodsConfig;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    private ConfigLoader() throws InstantiationException {
        throw new InstantiationException("Utility class : " + this.getClass().getName());
    }

    public static ResolutionMethodsConfig loadConfig(InputStream yamlFile) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(yamlFile, ResolutionMethodsConfig.class);
        } catch (IOException e) {
            logger.debug("Error reading ResolutionMethod configuration {}", e.getMessage());
            throw new ConfigLoaderException(e, -1);
        }

    }
}
