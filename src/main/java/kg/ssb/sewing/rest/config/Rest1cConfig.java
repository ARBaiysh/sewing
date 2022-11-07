package kg.ssb.sewing.rest.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class Rest1cConfig {
    @Value("${base1c.username}")
    String username;
    @Value("${base1c.password}")
    String password;
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }
}
