package com.example.dz_kafka.kafka.impl;

import com.example.dz_kafka.kafka.KafkaProducerService;
import com.example.dz_kafka.model.producer.MetricKafkaDTO;
import com.example.dz_kafka.service.MetricProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Value("${metric.topic.name}")
    String topicName;

    private final MetricProducerService metricService;
    private final KafkaTemplate<Object, Object> template;
    private final ObjectMapper objectMapper;


    @Override
    @Scheduled(fixedRate = 60000, initialDelay = 60000)
    public void sendMetrics() {
        try {
            MetricKafkaDTO metric = metricService.getMetric();
            this.template.send(topicName, objectMapper.writeValueAsString(metric));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
