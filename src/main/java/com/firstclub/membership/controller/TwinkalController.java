package com.firstclub.membership.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TwinkalController {
    @GetMapping("/health")
    public String health() {
        return "Twinkal, everything is working fine...woohooo";
    }
}
