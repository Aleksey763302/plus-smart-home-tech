package ru.yandex.practicum.telemetry.collector.service.avro.handler.hub.scenario;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.scenario.ScenarioAddedEvent;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.BaseEventAvroHandler;

import java.util.List;

@Component
@Qualifier("hub")
public class ScenarioAddedEventAvroHandler extends BaseEventAvroHandler<ScenarioAddedEventAvro, ScenarioAddedEvent> {
    public ScenarioAddedEventAvroHandler(KafkaEventProducerAvro producer) {
        super(producer);
    }

    @Override
    protected ScenarioAddedEventAvro mapToAvro(ScenarioAddedEvent event) {
        List<ScenarioConditionAvro> conditionAvros = event.getConditions().stream()
                .map(cond -> ScenarioConditionAvro.newBuilder()
                        .setDeviceId(cond.getSensorId())
                        .setType(ConditionTypeAvro.valueOf(cond.getType().name()))
                        .setOperation(ConditionOperationAvro.valueOf(cond.getOperation().name()))
                        .setValue( cond.getValue())
                        .build())
                .toList();

        List<DeviceActionAvro> actionAvros = event.getActions().stream()
                .map(act -> DeviceActionAvro.newBuilder()
                        .setSensorId(act.getSensorId())
                        .setType(ActionTypeAvro.valueOf(act.getType().name()))
                        .setValue(act.getValue())
                        .build())
                .toList();

        return ScenarioAddedEventAvro.newBuilder()
                .setName(event.getName())
                .setConditions(conditionAvros)
                .setActions(actionAvros)
                .build();
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.SCENARIO_ADDED;
    }

    @Override
    protected SpecificRecordBase wrap(ScenarioAddedEvent event, ScenarioAddedEventAvro payload) {
        return HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }
}
