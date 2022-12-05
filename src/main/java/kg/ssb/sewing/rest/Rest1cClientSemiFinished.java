package kg.ssb.sewing.rest;

import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "Rest1cClientSemiFinished", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientSemiFinished {
    @RequestMapping(method = RequestMethod.GET, value = "semi_finished?userUuid&role", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getSemiFinished(@RequestParam String userUuid,
                                           @RequestParam String role);

    @RequestMapping(method = RequestMethod.POST, value = "semi_finished", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> postSemiFinished(@RequestBody Object object);
}
