package ru.yandex.practicum.telemetry.collector.service.avro.handler.hub.device;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceAddedEvent;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.BaseEventAvroHandler;

@Component
@Qualifier("hub")
public class DeviceAddedEventAvroHandler extends BaseEventAvroHandler<DeviceAddedEventAvro,DeviceAddedEvent> {

    public DeviceAddedEventAvroHandler(KafkaEventProducerAvro producer){
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
        return HubEventAvro.newBuilder()
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .setHubId(event.getHubId())
                .build();
    }
}
