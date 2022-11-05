package kg.ssb.sewing.rest;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Rest1CClientProperties {
    private String url = "http://192.168.24.21";
    private String basePath = "/sewing/hs/serv/user";
    private String authStr = "MobileBackendSewing:sU9kobit";
}
