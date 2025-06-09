package ru.yandex.practicum.telemetry.collector.service.handler.hub.device;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceAddedEvent;
import ru.yandex.practicum.telemetry.collector.service.KafkaEventProducer;
import ru.yandex.practicum.telemetry.collector.service.handler.BaseEventHandler;

@Component
@Qualifier("hub")
public class DeviceAddedEventHandler extends BaseEventHandler<DeviceAddedEventAvro,DeviceAddedEvent> {

    public DeviceAddedEventHandler(KafkaEventProducer producer){
        super(producer);
    }

    @Override
    protected DeviceAddedEventAvro mapToAvro(DeviceAddedEvent event) {
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

    @Override
    protected SpecificRecordBase wrap(DeviceAddedEvent event, DeviceAddedEventAvro payload) {
        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }
}
