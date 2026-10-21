package com.beraldo.telemetry.repository;

import com.beraldo.telemetry.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
    List<Telemetry> findTop100ByOrderByCapturedAtDesc();
}
