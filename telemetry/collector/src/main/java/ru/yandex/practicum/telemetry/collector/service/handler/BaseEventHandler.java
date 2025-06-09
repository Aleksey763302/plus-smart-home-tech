package ru.yandex.practicum.telemetry.collector.service.handler;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;
import ru.yandex.practicum.telemetry.collector.service.KafkaEventProducer;

@RequiredArgsConstructor
public abstract class BaseEventHandler<T extends SpecificRecordBase, E extends BaseEvent> {
    protected final KafkaEventProducer producer;

    protected abstract T mapToAvro(E event);

    public abstract Enum<?> getMessageType();

    protected abstract SpecificRecordBase wrap(E event, T payload);

    public void handle(E event) {
        if (!event.getType().equals(getMessageType())) {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getType());
        }

        T payload = mapToAvro(event);
        SpecificRecordBase eventAvro = wrap(event, payload);
        producer.send(eventAvro);
    }
}
