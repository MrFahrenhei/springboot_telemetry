package com.beraldo.telemetry.dto;

public record TelemetryPayload(
        String hostname,
        Double cpuUsagePercentage,
        Double memoryTotalMb,
        Double memoryUsedMb,
        Double diskTotalGb,
        Double diskUsageGb,
        Long systemUptimeSeconds
) {
}
