package ru.yandex.practicum.telemetry.collector.service;


import jakarta.annotation.PreDestroy;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.serializer.GeneralAvroSerializer;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

import java.util.Properties;

@Component
public class KafkaEventProducer {
    private final Producer<Void, SpecificRecordBase> producer;

    public KafkaEventProducer() {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GeneralAvroSerializer.class.getName());

        this.producer = new KafkaProducer<>(config);
    }


    public void send(SpecificRecordBase event) {
        String topic = resolveTopic(event);

        ProducerRecord<Void, SpecificRecordBase> record = new ProducerRecord<>(topic, event);
        producer.send(record);
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

    @PreDestroy
    public void shutdown() {
        producer.close();
    }

}
