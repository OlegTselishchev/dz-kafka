package com.example.dz_kafka.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.client.metrics.name}", url = "${feign.client.metrics.url}")
public interface MetricFeignClient {

    @GetMapping("/actuator/metrics/{metricName}")
    String getMetric(@PathVariable String metricName);
}
