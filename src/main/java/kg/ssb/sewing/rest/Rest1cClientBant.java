package kg.ssb.sewing.rest;

import kg.ssb.sewing.dto.BantDTO;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(value = "best1cClientBant", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientBant {
    @RequestMapping(method = RequestMethod.GET, value = "bant", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BantDTO>> getAllBants();
}
