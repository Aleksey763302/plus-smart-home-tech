package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.device;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.DeviceAddedEventProto;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
@Qualifier("hub")
public class DeviceAddedEventProtoHandler extends BaseProtobufEventHandler<DeviceAddedEventProto> {

    protected DeviceAddedEventProtoHandler(EventSerializer<DeviceAddedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.DEVICE_ADDED;
    }
}
