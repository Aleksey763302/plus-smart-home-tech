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

    private final KafkaTemplate<String, HubEventProto> hubEventKafkaTemplate;
    private final KafkaTemplate<String, SensorEventProto> sensorEventKafkaTemplate;

    public void send(HubEventProto event) {
        hubEventKafkaTemplate.send("telemetry.hubs.v1", event);
    }

    public void send(SensorEventProto event) {
        sensorEventKafkaTemplate.send("telemetry.sensors.v1", event);
    }
}
