package ru.yandex.practicum.telemetry.collector.service.proto.handler.hub.device;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

@Component
@Qualifier("hub")
public class DeviceAddedEventProtoHandler extends BaseEventProtoHandler<HubEventProto> {
    public DeviceAddedEventProtoHandler(KafkaEventProducerProto producer) {
        super(producer);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.DEVICE_ADDED;
    }

    @Override
    public void handle(HubEventProto event) {
        producer.send(event);
    }

}
