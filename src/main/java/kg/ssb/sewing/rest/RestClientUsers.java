package kg.ssb.sewing.rest;

import kg.ssb.sewing.payload.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class RestClientUsers {
    private RestTemplate restTemplate;
    private Rest1CClientProperties properties;

    public RestClientUsers(Rest1CClientProperties properties) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new Rest1CErrorHandler());
        this.properties = properties;
    }

    public Iterable<SignUpRequest> findUsersByBase1C() throws URISyntaxException {
        String base64Creds = Base64.getEncoder().encodeToString(properties.getAuthStr().getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<SignUpRequest>> response = restTemplate.exchange(properties.getUrl() + properties.getBasePath(), HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        return response.getBody();
    }

    public Iterable<SignUpRequest> findUserByBase1C(String username) {
        String base64Creds = Base64.getEncoder().encodeToString(properties.getAuthStr().getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);

        List<SignUpRequest> requests = new ArrayList<>();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUuid("");
        try {
            ResponseEntity<List<SignUpRequest>> response = restTemplate.exchange(properties.getUrl() + properties.getBasePath() + "?personalId=" + username, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
            });
            return response.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            requests.add(signUpRequest);
            return requests;
        }
    }
}
