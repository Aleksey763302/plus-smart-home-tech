package ru.yandex.practicum.telemetry.collector.service;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;

@RequiredArgsConstructor
public abstract class BaseEventHandler<T, E extends BaseEvent> {
    protected final EventSerializer<T> serializer;

    protected abstract T map(E event);

    public abstract Enum<?> getMessageType();

    public void handle(E event) {
        if (!event.getType().equals(getMessageType())) {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getType());
        }

        T message = map(event);
        serializer.send(message);
    }
}
