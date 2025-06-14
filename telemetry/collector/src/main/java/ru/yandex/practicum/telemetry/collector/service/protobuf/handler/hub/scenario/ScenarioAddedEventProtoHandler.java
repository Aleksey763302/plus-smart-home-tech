package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.scenario;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.*;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

import java.util.List;

@Component
public class ScenarioAddedEventProtoHandler extends BaseProtobufEventHandler<ScenarioAddedEventProto> {

    protected ScenarioAddedEventProtoHandler(EventSerializer<ScenarioAddedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.SCENARIO_ADDED;
    }
}
