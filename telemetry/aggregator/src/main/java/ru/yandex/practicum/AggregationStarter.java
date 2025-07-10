package ru.yandex.practicum;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.service.AggregatorService;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class AggregationStarter {
    private final KafkaTemplate<String, SensorsSnapshotAvro> producer;
    private final KafkaConsumer<String, SensorEventAvro> consumer;

    private final AggregatorService aggregatorService;

    public AggregationStarter(KafkaTemplate<String, SensorsSnapshotAvro> producer,
                              ConsumerFactory<String, SensorEventAvro> consumerFactory,
                              AggregatorService aggregatorService) {
        this.producer = producer;
        this.consumer = (KafkaConsumer<String, SensorEventAvro>) consumerFactory.createConsumer();
        this.aggregatorService = aggregatorService;
    }

    public void start() {
        consumer.subscribe(Collections.singletonList("telemetry.sensors.v1"));
        Executors.newSingleThreadExecutor().submit(this::pollLoop);
    }

    private void pollLoop() {
        Map<String, SensorsSnapshotAvro> state = new HashMap<>();

        while (true) {
            ConsumerRecords<String, SensorEventAvro> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, SensorEventAvro> record : records) {
                SensorEventAvro event = record.value();

                Optional<SensorsSnapshotAvro> optionalSensorsSnapshotAvro = aggregatorService.updateState(event);
                if(optionalSensorsSnapshotAvro.isPresent()){
                    SensorsSnapshotAvro snapshot = optionalSensorsSnapshotAvro.get();
                    String hubId = snapshot.getHubId().toString();

                    producer.send(new ProducerRecord<>("telemetry.snapshots.v1", hubId, snapshot));
                    log.info("Published snapshot for hub: {}", hubId);
                }

            }
        }
    }

}

