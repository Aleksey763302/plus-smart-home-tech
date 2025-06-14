package ru.yandex.practicum.telemetry.collector.service.proto.handler.hub.scenario;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioRemovedEventProto;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

@Component
@Qualifier("hub")
public class ScenarioRemovedEventProtoHandler extends BaseEventProtoHandler<ScenarioRemovedEventProto> {
    public ScenarioRemovedEventProtoHandler(KafkaEventProducerProto producer) {
        super(producer);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.SCENARIO_REMOVED;
    }
}
