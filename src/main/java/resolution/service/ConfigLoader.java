package resolution.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resolution.ResolutionMethodsConfig;

import java.io.InputStream;

public class ConfigLoader {
    static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public static ResolutionMethodsConfig loadConfig(InputStream yamlFile) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(yamlFile, ResolutionMethodsConfig.class);
        } catch (Exception e) {
            logger.debug("Error reading ResolutionMethod configuration {}", e);
            throw new RuntimeException(e);
        }

    }
}
