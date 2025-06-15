package ru.yandex.practicum.telemetry.collector.service.avro;


import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

@Component
@RequiredArgsConstructor
public class KafkaEventProducerAvro {
    private final KafkaTemplate<String,SpecificRecordBase> kafkaTemplate;


    public void send(SpecificRecordBase event) {
        String topic = resolveTopic(event);

        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(topic, event);
        kafkaTemplate.send(record);
    }

    private String resolveTopic(SpecificRecordBase event) {
        if (event instanceof SensorEventAvro) {
            return "telemetry.sensors.v1";
        } else if (event instanceof HubEventAvro) {
            return "telemetry.hubs.v1";
        } else {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getClass());
        }
    }

}
