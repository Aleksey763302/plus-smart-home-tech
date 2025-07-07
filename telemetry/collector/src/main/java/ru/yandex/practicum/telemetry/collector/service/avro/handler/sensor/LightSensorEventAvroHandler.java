package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.event.LightSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.BaseEventAvroHandler;

@Component
@Qualifier("sensor")
public class LightSensorEventAvroHandler extends BaseEventAvroHandler<LightSensorAvro,LightSensorEvent> {
    public LightSensorEventAvroHandler(KafkaEventProducerAvro producer){
        super(producer);
    }


    @Override
    protected LightSensorAvro mapToAvro(LightSensorEvent event) {
        return LightSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setLuminosity(event.getLuminosity())
                .build();
    }

    @Override
    protected SpecificRecordBase wrap(LightSensorEvent event, LightSensorAvro payload) {
        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
