package kr.co.polycube.backendtest.global.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.boot.env.YamlPropertySourceLoader;

import java.io.IOException;
import java.util.List;

@Configuration
public class EnvConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        ClassPathResource pathResource = new ClassPathResource("env.yml");

        try {
            List<PropertySource<?>> propertySources = loader.load("env", pathResource);
            propertySources.forEach(environment.getPropertySources()::addFirst);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load yaml configuration from " + pathResource, e);
        }
    }
}
