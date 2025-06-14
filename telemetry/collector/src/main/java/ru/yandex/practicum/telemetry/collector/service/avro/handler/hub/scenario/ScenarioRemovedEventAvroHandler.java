package ru.yandex.practicum.telemetry.collector.service.avro.handler.hub.scenario;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.scenario.ScenarioRemovedEvent;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;

@Component
public class ScenarioRemovedEventAvroHandler extends BaseEventHandler<ScenarioRemovedEventAvro, ScenarioRemovedEvent> {

    public ScenarioRemovedEventAvroHandler(EventSerializer<ScenarioRemovedEventAvro> serializer) {
        super(serializer);
    }

    @Override
    protected ScenarioRemovedEventAvro map(ScenarioRemovedEvent event) {
        return ScenarioRemovedEventAvro.newBuilder()
                .setName(event.getName())
                .build();
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.SCENARIO_REMOVED;
    }

}
