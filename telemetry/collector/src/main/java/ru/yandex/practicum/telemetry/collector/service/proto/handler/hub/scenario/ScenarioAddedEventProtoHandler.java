package ru.yandex.practicum.telemetry.collector.service.proto.handler.hub.scenario;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioAddedEventProto;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

@Component
@Qualifier("hub")
public class ScenarioAddedEventProtoHandler extends BaseEventProtoHandler<ScenarioAddedEventProto> {
    public ScenarioAddedEventProtoHandler(KafkaEventProducerProto producer) {
        super(producer);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.SCENARIO_ADDED;
    }
}
