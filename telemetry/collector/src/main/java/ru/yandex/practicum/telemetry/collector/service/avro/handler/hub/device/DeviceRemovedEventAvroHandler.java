package ru.yandex.practicum.telemetry.collector.service.avro.handler.hub.device;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceRemovedEvent;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.AvroEventHandler;

@Component
public class DeviceRemovedEventAvroHandler extends BaseEventHandler<DeviceRemovedEventAvro,DeviceRemovedEvent> {

    public DeviceRemovedEventAvroHandler(EventSerializer<DeviceRemovedEventAvro> serializer) {
        super(serializer);
    }

    @Override
    protected DeviceRemovedEventAvro map(DeviceRemovedEvent event) {
        return DeviceRemovedEventAvro.newBuilder()
                .setId(event.getId())
                .build();
    }

    @Override
    public DeviceActionType getMessageType() {
        return DeviceActionType.DEVICE_REMOVED;
    }

}
