package ru.yandex.practicum.telemetry.collector.service.avro.handler;

import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;

public abstract class BaseEventAvroHandler<T extends SpecificRecordBase, E extends BaseEvent> {
    protected final KafkaEventProducerAvro producer;

    protected BaseEventAvroHandler(KafkaEventProducerAvro producer) {
        this.producer = producer;
    }

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
