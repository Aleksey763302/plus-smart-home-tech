package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.device;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.DeviceRemovedEventProto;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class DeviceRemovedEventProtoHandler extends BaseProtobufEventHandler<DeviceRemovedEventProto> {

    protected DeviceRemovedEventProtoHandler(EventSerializer<DeviceRemovedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.DEVICE_REMOVED;
    }
}
