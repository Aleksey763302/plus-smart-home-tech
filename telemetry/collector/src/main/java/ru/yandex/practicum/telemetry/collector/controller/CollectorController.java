package ru.yandex.practicum.telemetry.collector.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.telemetry.collector.service.handler.BaseEventHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "events", consumes = MediaType.APPLICATION_JSON_VALUE)
public class CollectorController {
    private final Map<Enum<?>, BaseEventHandler<?, ?>> sensorEventHandlerMap;
    private final Map<Enum<?>, BaseEventHandler<?, ?>> hubEventHandlerMap;

    public CollectorController(
            @Qualifier("sensor") List<BaseEventHandler<?, ?>> sensorHandlers,
            @Qualifier("hub") List<BaseEventHandler<?, ?>> hubHandlers
    ) {
        this.sensorEventHandlerMap = sensorHandlers.stream()
                .collect(Collectors.toMap(
                        BaseEventHandler::getMessageType,
                        Function.identity()
                ));

        this.hubEventHandlerMap = hubHandlers.stream()
                .collect(Collectors.toMap(
                        BaseEventHandler::getMessageType,
                        Function.identity()
                ));
    }

    @PostMapping("/sensors")
    public void collectSensorEvent(@Valid @RequestBody BaseEvent request) {
        BaseEventHandler<?, ?> sensorEventHandler = sensorEventHandlerMap.get(request.getType());
        if (Objects.isNull(sensorEventHandler)) {
            throw new IllegalArgumentException("Не могу найти обработчик для события " + request.getType());
        }
        ((BaseEventHandler<?,BaseEvent>) sensorEventHandler).handle(request);

    }

    @PostMapping("/hubs")
    public void collectHubEvent(@Valid @RequestBody BaseEvent request) {
        BaseEventHandler<?, ?> hubEventHandler = hubEventHandlerMap.get(request.getType());
        if (Objects.isNull(hubEventHandler)) {
            throw new IllegalArgumentException("Не могу найти обработчик для события " + request.getType());
        }
        ((BaseEventHandler<?,BaseEvent>) hubEventHandler).handle(request);

    }
}
