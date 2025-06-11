package ru.yandex.practicum.telemetry.collector.service.handler.hub.device;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceRemovedEvent;
import ru.yandex.practicum.telemetry.collector.service.KafkaEventProducer;
import ru.yandex.practicum.telemetry.collector.service.handler.BaseEventHandler;

@Component
@Qualifier("hub")
public class DeviceRemovedEventHandler extends BaseEventHandler<DeviceRemovedEventAvro,DeviceRemovedEvent> {
    public DeviceRemovedEventHandler(KafkaEventProducer producer){
        super(producer);
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvro(DeviceRemovedEvent event) {
        return DeviceRemovedEventAvro.newBuilder()
                .setId(event.getId())
                .build();
    }

    @Override
    public DeviceActionType getMessageType() {
        return DeviceActionType.DEVICE_REMOVED;
    }

    @Override
    protected SpecificRecordBase wrap(DeviceRemovedEvent event, DeviceRemovedEventAvro payload) {
        return HubEventAvro.newBuilder()
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .setHubId(event.getHubId())
                .build();
    }
}
