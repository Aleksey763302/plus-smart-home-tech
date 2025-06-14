package ru.yandex.practicum.telemetry.collector.service.avro.handler.hub.device;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceAddedEvent;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.AvroEventHandler;

@Component
public class DeviceAddedEventAvroHandler extends BaseEventHandler<DeviceAddedEventAvro, DeviceAddedEvent> {

    public DeviceAddedEventAvroHandler(EventSerializer<DeviceAddedEventAvro> serializer) {
        super(serializer);
    }

    @Override
    protected DeviceAddedEventAvro map(DeviceAddedEvent event) {
        DeviceTypeAvro deviceTypeAvro = DeviceTypeAvro.valueOf(event.getDeviceType().name());
        return DeviceAddedEventAvro.newBuilder()
                .setId(event.getId())
                .setType(deviceTypeAvro)
                .build();
    }

    @Override
    public DeviceActionType getMessageType() {
        return DeviceActionType.DEVICE_ADDED;
    }

}
