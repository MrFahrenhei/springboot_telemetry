package com.beraldo.telemetry.controller;

import com.beraldo.telemetry.dto.TelemetryPayload;
import com.beraldo.telemetry.entity.Telemetry;
import com.beraldo.telemetry.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/telemetry")
@RequiredArgsConstructor
public class TelemetryController {
    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);
    private final TelemetryService telemetryService;

    @PostMapping
    public ResponseEntity<Void> receiveTelemetry(@RequestBody TelemetryPayload payload){
        log.info("Received telemetry data from agent for hostname: {}", payload.hostname());
        telemetryService.processTelemetry(payload);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Telemetry>> getLatestTelemetry(){
        log.info("Received request to fetch latest telemetry records");
        List<Telemetry> records = telemetryService.getLatestTelemetry();
        return ResponseEntity.ok(records);
    }
}
