package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import ru.yandex.practicum.telemetry.collector.model.event.LightSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.AvroEventHandler;

@Component
public class LightSensorEventAvroHandler extends BaseEventHandler<LightSensorAvro, LightSensorEvent> {

    public LightSensorEventAvroHandler(EventSerializer<LightSensorAvro> serializer) {
        super(serializer);
    }

    @Override
    protected LightSensorAvro map(LightSensorEvent event) {
        return LightSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setLuminosity(event.getLuminosity())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
