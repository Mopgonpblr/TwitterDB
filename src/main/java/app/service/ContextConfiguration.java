package app.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ContextConfiguration {

    @Bean
    @ConditionalOnProperty("property.condition")
    public String ThisIsMyFirstConditionalBean() {
        return "This will appear only if @ConditionalOnProperty statement is true";
    }
}
