package kg.ssb.sewing.rest;

import kg.ssb.sewing.dto.WorkplaceDTO;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(value = "rest1cClientWorkplace", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientWorkplace {
    @RequestMapping(method = RequestMethod.GET, value = "workplace", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkplaceDTO>> getWorkplace();

}
