package control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages = {"control", "model", "view", "resolution"})
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public MyProperties myProperties() {
        return new MyProperties("config.properties");
    }

}


