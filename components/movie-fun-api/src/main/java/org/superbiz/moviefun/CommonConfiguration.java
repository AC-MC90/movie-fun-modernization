package org.superbiz.moviefun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfiguration {

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }
}
