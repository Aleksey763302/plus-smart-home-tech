package ru.yandex.practicum.telemetry.collector.service.proto;

import com.google.protobuf.MessageLite;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

@RequiredArgsConstructor
@Component
public class KafkaEventProducerProto {
    private final KafkaTemplate<String, MessageLite> kafkaTemplate;

    public void send(MessageLite event){
        String topic = resolveTopic(event);

        ProducerRecord<String, MessageLite> record = new ProducerRecord<>(topic, event);
        kafkaTemplate.send(record);
    }

    private String resolveTopic(MessageLite event) {
        if (event instanceof SensorEventProto) {
            return "telemetry.sensors.v1";
        } else if (event instanceof HubEventProto) {
            return "telemetry.hubs.v1";
        } else {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getClass());
        }
    }
}
