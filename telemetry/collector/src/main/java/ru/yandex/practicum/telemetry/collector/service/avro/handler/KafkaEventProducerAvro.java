package ru.yandex.practicum.telemetry.collector.service.avro.handler;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

@Component
public class KafkaEventProducerAvro<T extends SpecificRecordBase> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaEventProducerAvro(@Qualifier("kafkaTemplateAvro") KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(T event) {
        String topic = resolveTopic(event);
        kafkaTemplate.send(topic, event);
    }

    private String resolveTopic(T event) {
        if (event instanceof SensorEventAvro ) {
            return "telemetry.sensors.v1";
        } else if (event instanceof HubEventAvro) {
            return "telemetry.hubs.v1";
        } else {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getClass());
        }
    }
}
