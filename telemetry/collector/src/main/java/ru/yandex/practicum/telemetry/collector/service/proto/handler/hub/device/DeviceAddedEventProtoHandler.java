package ru.yandex.practicum.telemetry.collector.service.proto.handler.hub.device;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

@Component
@Qualifier("hub")
public class DeviceAddedEventProtoHandler extends BaseEventProtoHandler<HubEventProto> {
    public DeviceAddedEventProtoHandler( KafkaEventProducerAvro producerAvro) {
        super(producerAvro);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.DEVICE_ADDED;
    }

}
