package kg.ssb.sewing.rest;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Rest1CClientProperties {
    @Value("${base1c.url}")
    private String url;
    @Value("${base1c.users-path}")
    private String basePath;
    @Value("${base1c.login}:${base1c.pass}")
    private String authStr;

}
