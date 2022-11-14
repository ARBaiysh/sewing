package kg.ssb.sewing.rest;

import kg.ssb.sewing.dto.EmployeeDTO;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(value = "best1cClientBant", url = "${base1c.url}", configuration = Rest1cConfig.class)
public interface Rest1cClientEmployee {
    @RequestMapping(method = RequestMethod.GET, value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EmployeeDTO>> getAllEmployees();

//    @RequestMapping(method = RequestMethod.POST, value = "tabel", produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<?> employeeDetailExport(@RequestBody List<EmployeeDetailExportDTO> employeeDetailExportDTOList);
}
