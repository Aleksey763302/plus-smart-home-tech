package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.hub.device;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.DeviceAddedEventProto;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class DeviceAddedEventProtoHandler extends BaseProtobufEventHandler<DeviceAddedEventProto> {

    protected DeviceAddedEventProtoHandler(EventSerializer<DeviceAddedEventProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return DeviceActionType.DEVICE_ADDED;
    }
}
