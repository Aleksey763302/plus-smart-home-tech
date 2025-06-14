package ru.yandex.practicum.telemetry.collector.service.protobuf.handler;


import com.google.protobuf.MessageLite;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

@Component
public class KafkaEventProducerProto<T extends MessageLite>{
    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaEventProducerProto(@Qualifier("kafkaTemplateProto")KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(T event) {
        String topic = resolveTopic(event);
        kafkaTemplate.send(topic, event);
    }

    private String resolveTopic(T event) {
        if (event instanceof SensorEventProto) {
            return "telemetry.sensors.v1";
        } else if (event instanceof HubEventProto) {
            return "telemetry.hubs.v1";
        } else {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getClass());
        }
    }

}
