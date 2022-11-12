package kg.ssb.sewing.rest;

import kg.ssb.sewing.dto.LeaderDto;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(value = "Rest1cClientLeader", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientLeader {
    @RequestMapping(method = RequestMethod.GET, value = "leaders", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LeaderDto>> get();
}
