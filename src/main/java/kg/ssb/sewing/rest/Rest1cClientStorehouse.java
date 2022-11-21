package kg.ssb.sewing.rest;

import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(value = "Rest1cClientStorehouse", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientStorehouse {

    @RequestMapping(method = RequestMethod.GET, value = "storehouse", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getStorehouse();
}
