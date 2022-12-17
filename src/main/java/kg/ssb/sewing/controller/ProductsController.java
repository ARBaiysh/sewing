package kg.ssb.sewing.controller;

import kg.ssb.sewing.rest.Rest1cProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_MASTER','ROLE_SEAMSTRESS','ROLE_MASTERS_LEADER','ROLE_HEAD_OF_CUTTING','ROLE_CONTROLLER','ROLE_PRINTER','ROLE_PACKER')")
@CrossOrigin
public class ProductsController {

    private final Rest1cProducts rest1cProducts;


    @GetMapping()
    public ResponseEntity<Object> getProducts(@RequestParam String userUuid) {
        log.info("Start getProducts");
        ResponseEntity<Object> storehouse = rest1cProducts.getUserByPersonalId(userUuid);
        log.info("Finish getProducts response status-{}", storehouse.getStatusCode());
        return new ResponseEntity<>(storehouse.getBody(), HttpStatus.OK);
    }

}
