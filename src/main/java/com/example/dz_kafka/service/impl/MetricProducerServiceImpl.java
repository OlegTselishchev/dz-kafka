package com.example.dz_kafka.service.impl;

import com.example.dz_kafka.feign.MetricFeignClient;
import com.example.dz_kafka.model.mapper.MetricDTO;
import com.example.dz_kafka.model.producer.MetricKafkaDTO;
import com.example.dz_kafka.service.MetricProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MetricProducerServiceImpl implements MetricProducerService {

    private final MetricFeignClient metricClient;

    @Value("${list.metric.names}")
    private List<String> metricNames;

    private String cpuMetric = "process.cpu.usage";
    private String memoryMetric = "jvm.memory.used";

    @Override
    public MetricKafkaDTO getMetric() {
        List<String> listMetrics = metricNames.stream().map(metricClient::getMetric).toList();
            return convertor(listMetrics);
    }

    private MetricKafkaDTO convertor(List<String> list) {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Double> metrics = new HashMap<>();
            for (String metric : list) {
                MetricDTO metricDTO = objectMapper.readValue(metric, MetricDTO.class);
                metrics.put(metricDTO.getName(), metricDTO.getMeasurements().getFirst().getValue());
            }
            return MetricKafkaDTO.builder()
                    .date(date.format(new Date()))
                    .time(time.format(new Date()))
                    .cpu(metrics.get(cpuMetric))
                    .memory(metrics.get(memoryMetric))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
