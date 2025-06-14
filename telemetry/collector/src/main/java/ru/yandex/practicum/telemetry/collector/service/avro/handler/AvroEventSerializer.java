package ru.yandex.practicum.telemetry.collector.service.avro.handler;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;

@Component
@RequiredArgsConstructor
public class AvroEventSerializer implements EventSerializer<SpecificRecordBase> {
    private final KafkaEventProducerAvro<SpecificRecordBase> producer;


    @Override
    public void send(SpecificRecordBase message) {
        producer.send(message);
    }
}
