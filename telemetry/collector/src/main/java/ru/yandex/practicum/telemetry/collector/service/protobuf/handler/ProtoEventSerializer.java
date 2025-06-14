package ru.yandex.practicum.telemetry.collector.service.protobuf.handler;

import com.google.protobuf.MessageLite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;

@RequiredArgsConstructor
@Component
public class ProtoEventSerializer implements EventSerializer<MessageLite> {
    private final KafkaEventProducerProto<MessageLite> producer;

    @Override
    public void send(MessageLite message) {
        producer.send(message);
    }
}
