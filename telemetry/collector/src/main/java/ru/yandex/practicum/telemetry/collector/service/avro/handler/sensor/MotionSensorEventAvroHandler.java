package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.telemetry.collector.model.event.MotionSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.AvroEventHandler;

@Component
public class MotionSensorEventAvroHandler extends BaseEventHandler<MotionSensorAvro, MotionSensorEvent> {

    public MotionSensorEventAvroHandler(EventSerializer<MotionSensorAvro> serializer) {
        super(serializer);
    }

    @Override
    protected MotionSensorAvro map(MotionSensorEvent event) {
        return MotionSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setMotion(event.isMotion())
                .setVoltage(event.getVoltage())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
