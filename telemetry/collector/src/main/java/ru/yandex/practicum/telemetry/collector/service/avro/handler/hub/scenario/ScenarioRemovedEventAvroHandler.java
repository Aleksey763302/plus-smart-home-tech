package ru.yandex.practicum.telemetry.collector.service.avro.handler.hub.scenario;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.scenario.ScenarioRemovedEvent;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.BaseEventAvroHandler;

@Component
@Qualifier("hub")
public class ScenarioRemovedEventAvroHandler extends BaseEventAvroHandler<ScenarioRemovedEventAvro, ScenarioRemovedEvent> {
    public ScenarioRemovedEventAvroHandler(KafkaEventProducerAvro producer) {
        super(producer);
    }

    @Override
    protected ScenarioRemovedEventAvro mapToAvro(ScenarioRemovedEvent event) {
        return ScenarioRemovedEventAvro.newBuilder()
                .setName(event.getName())
                .build();
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.SCENARIO_REMOVED;
    }

    @Override
    protected SpecificRecordBase wrap(ScenarioRemovedEvent event, ScenarioRemovedEventAvro payload) {
        return HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }
}
