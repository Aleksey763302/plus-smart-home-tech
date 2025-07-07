package ru.yandex.practicum.telemetry.collector.service.proto;

import com.google.protobuf.MessageLite;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.DeviceAddedEventProto;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaEventProducerProto {

    private final KafkaTemplate<String, HubEventProto> hubEventKafkaTemplate;
    private final KafkaTemplate<String, SensorEventProto> sensorEventKafkaTemplate;
    private final KafkaTemplate<String, DeviceAddedEventProto> hubEventKafkaTemplate2;

    private final KafkaTemplate<String, MessageLite> kafkaTemplate;

    public void send(HubEventProto event) {
        hubEventKafkaTemplate.send("telemetry.hubs.v1", event);
    }

    public void send(SensorEventProto event) {
        sensorEventKafkaTemplate.send("telemetry.sensors.v1", event);
    }

    public void send(DeviceAddedEventProto event) {
        System.out.println("Отправлен :: " + event.getDefaultInstanceForType());
        hubEventKafkaTemplate2.send("telemetry.hubs.v1", event);

    }

     public void send(String topic, MessageLite event){
         System.out.println("Отправлен :: " + event.getDefaultInstanceForType());

         CompletableFuture<SendResult<String, MessageLite>> future = kafkaTemplate.send(topic, event);

         future.whenComplete((result, ex) -> {
             if (ex == null) {
                 RecordMetadata meta = result.getRecordMetadata();
                 log.info("✅ Успешно отправлено в Kafka: topic={}, partition={}, offset={}",
                         meta.topic(), meta.partition(), meta.offset());
             } else {
                 log.error("❌ Ошибка при отправке в Kafka: {}", ex.getMessage(), ex);
             }
         });
     }
}
