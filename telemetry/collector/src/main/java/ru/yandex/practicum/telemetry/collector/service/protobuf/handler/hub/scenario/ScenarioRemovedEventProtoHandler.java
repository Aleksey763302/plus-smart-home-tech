package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.scenario;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioRemovedEventProto;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;

import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class ScenarioRemovedEventProtoHandler extends BaseProtobufEventHandler<ScenarioRemovedEventProto> {

    protected ScenarioRemovedEventProtoHandler(EventSerializer<ScenarioRemovedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.SCENARIO_REMOVED;
    }
}
