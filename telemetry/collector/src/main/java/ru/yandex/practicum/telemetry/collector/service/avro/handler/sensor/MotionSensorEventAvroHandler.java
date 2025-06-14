package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.event.MotionSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.BaseEventAvroHandler;

@Component
@Qualifier("sensor")
public class MotionSensorEventAvroHandler extends BaseEventAvroHandler<MotionSensorAvro,MotionSensorEvent> {
    public MotionSensorEventAvroHandler(KafkaEventProducerAvro producer){
        super(producer);
    }


    @Override
    protected MotionSensorAvro mapToAvro(MotionSensorEvent event) {
        return MotionSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setMotion(event.isMotion())
                .setVoltage(event.getVoltage())
                .build();
    }

    @Override
    protected SpecificRecordBase wrap(MotionSensorEvent event, MotionSensorAvro payload) {
        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
