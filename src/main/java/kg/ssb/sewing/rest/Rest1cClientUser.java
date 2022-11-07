package kg.ssb.sewing.rest;

import kg.ssb.sewing.dto.SignUpRequestDTO;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "rest1cClientUser", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientUser {
    @RequestMapping(method = RequestMethod.GET, value = "user?personalId=", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SignUpRequestDTO> getUserByPersonalId(@RequestParam("personalId") String personalId);

}
