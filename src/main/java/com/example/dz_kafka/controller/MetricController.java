package com.example.dz_kafka.controller;


import com.example.dz_kafka.model.producer.MetricKafkaDTO;
import com.example.dz_kafka.service.MetricService;
import com.example.dz_kafka.service.MetricProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/rest")
@RequiredArgsConstructor
public class MetricController {

    private final MetricProducerService metricServiceProducer;
    private final MetricService metricServiceConsumer;

    @PostMapping("/send")
    public ResponseEntity<?> metrics() {
        try {
            MetricKafkaDTO metric = metricServiceProducer.getMetric();
            return ResponseEntity.ok(metric);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/metrics")
    public ResponseEntity<?> metricAll() {
        return ResponseEntity.ok(metricServiceConsumer.findAll());
    }

    @GetMapping("/metric/{id}")
    public ResponseEntity<?> metricById(@PathVariable Integer id) {
        return ResponseEntity.ok(metricServiceConsumer.findById(id));
    }
}
