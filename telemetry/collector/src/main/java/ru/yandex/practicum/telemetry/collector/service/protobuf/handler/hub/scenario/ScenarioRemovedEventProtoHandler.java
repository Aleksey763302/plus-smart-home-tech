package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.scenario;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioRemovedEventProto;

import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
@Qualifier("hub")
public class ScenarioRemovedEventProtoHandler extends BaseProtobufEventHandler<ScenarioRemovedEventProto> {

    protected ScenarioRemovedEventProtoHandler(EventSerializer<ScenarioRemovedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.SCENARIO_REMOVED;
    }
}
