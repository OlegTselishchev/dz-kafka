package com.example.dz_kafka.kafka.impl;

import com.example.dz_kafka.model.entity.Metrics;
import com.example.dz_kafka.repository.MetricRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl {

    private final KafkaTemplate<Object, Object> kafkaTemplates;
    private final MetricRepository metricRepository;
    private final ObjectMapper objectMapper;


    private static final String TOPIC_NAME = "${message.topic.name}";
    private static final String TOPIC_NAME_DLT = "${message.topic.name.dlt}";


    @KafkaListener(id = "metricsConsumerGroup", topics = TOPIC_NAME)
    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2))
    public void listener(String metricsMessage) {
        try {
            Metrics metrics = objectMapper.readValue(metricsMessage, Metrics.class);
            metricRepository.save(metrics);
            log.info("Save metrics: {}", metrics);
        } catch (Exception e) {
            kafkaTemplates.send(TOPIC_NAME_DLT, metricsMessage);
        }
    }

    @KafkaListener(id = "dltGroup", topics = TOPIC_NAME_DLT)
    public void dltListen(byte[] in) {
        log.info("Received from DLT: {}", new String(in));
    }

    @DltHandler
    public void listenDlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received from DLT: {}, topic: {}, offset: {}", in, topic, offset);
    }
}
