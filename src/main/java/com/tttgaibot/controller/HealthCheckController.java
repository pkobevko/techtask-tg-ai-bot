package com.tttgaibot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health check controller")
@RestController
public class HealthCheckController {
    @GetMapping("/health")
    @Operation(summary = "Health check")
    public String healthCheck() {
        return "OK";
    }
}
