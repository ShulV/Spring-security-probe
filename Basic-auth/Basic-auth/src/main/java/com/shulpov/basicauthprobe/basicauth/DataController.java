package com.shulpov.basicauthprobe.basicauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/test")
    public String getData() {
        return "Some test data!";
    }
}
