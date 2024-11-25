package com.folau.datasources.lender.entity;

import com.folau.datasources.house.entity.House;
import com.folau.datasources.house.entity.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Lender", description = "Lender Operations")
@RequestMapping("/lenders")
@RestController
@Slf4j
public class LenderController {

    @Autowired
    private LenderService lenderService;

    @Operation(summary = "Sign Up", description = "Sign up")
    @PostMapping(value = "/signup")
    public ResponseEntity<Lender> signup(@RequestBody Lender lender) {

        log.info("signup lender={}", lender);

        Lender savedLender = lenderService.save(lender);
        return new ResponseEntity<>(savedLender, OK);
    }
}
