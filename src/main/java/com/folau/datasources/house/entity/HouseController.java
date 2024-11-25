package com.folau.datasources.house.entity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "House", description = "House Operations")
@RequestMapping("/houses")
@RestController
@Slf4j
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Operation(summary = "Sign Up", description = "Sign up")
    @PostMapping(value = "/signup")
    public ResponseEntity<House> signup(@RequestBody House house) {

        log.info("signup house={}", house);

        House savedHouse = houseService.save(house);
        return new ResponseEntity<>(savedHouse, OK);
    }
}
