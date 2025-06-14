package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.device;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.DeviceRemovedEventProto;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
@Qualifier("hub")
public class DeviceRemovedEventProtoHandler extends BaseProtobufEventHandler<DeviceRemovedEventProto> {

    protected DeviceRemovedEventProtoHandler(EventSerializer<DeviceRemovedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return HubEventProto.PayloadCase.DEVICE_REMOVED;
    }
}
