package kg.ssb.sewing.rest;

import kg.ssb.sewing.dto.CutterDTO;
import kg.ssb.sewing.dto.EmployeeDetailExDTO;
import kg.ssb.sewing.dto.ResponseDTO;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "Rest1cClientCutter", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientCutter {
    @RequestMapping(method = RequestMethod.GET, value = "cutter?userUuid", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getCutterByUserUuid(@RequestParam("userUuid") String userUuid);

    @RequestMapping(method = RequestMethod.POST, value = "cutter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> setORCodeTo1c(@RequestBody CutterDTO cutterDTO);

    @RequestMapping(method = RequestMethod.POST, value = "cutter/cut", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> setCutTo1c(@RequestBody Object object);



}
