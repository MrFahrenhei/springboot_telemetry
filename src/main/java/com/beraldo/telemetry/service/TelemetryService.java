package com.beraldo.telemetry.service;

import com.beraldo.telemetry.dto.TelemetryPayload;
import com.beraldo.telemetry.entity.Telemetry;
import com.beraldo.telemetry.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelemetryService {
    private static final Logger log = LoggerFactory.getLogger(TelemetryService.class);
    private final TelemetryRepository repo;

    @Transactional
    public void processTelemetry(TelemetryPayload payload){
        log.debug("Processing incoming telemetry payload for hostname: {}", payload.hostname());

        Telemetry telemetry = Telemetry.builder()
                .hostname(payload.hostname())
                .cpuUsagePercentage(payload.cpuUsagePercentage())
                .memoryTotalMb(payload.memoryTotalMb())
                .memoryUsedMb(payload.memoryUsedMb())
                .diskTotalGb(payload.diskTotalGb())
                .diskUsageGb(payload.diskUsageGb())
                .systemUptimeSeconds(payload.systemUptimeSeconds())
                .build();
        repo.save(telemetry);
        log.debug("Telemetry data successfully persisted for hostname: {}", payload.hostname());
    }

    @Transactional(readOnly = true)
    public List<Telemetry> getLatestTelemetry(){
        log.debug("Fetching the latest 100 telemetry records");
        return repo.findTop100ByOrderByCapturedAtDesc();
    }

}
