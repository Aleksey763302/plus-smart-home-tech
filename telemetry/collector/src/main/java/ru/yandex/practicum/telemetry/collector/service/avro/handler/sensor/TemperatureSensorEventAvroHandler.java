package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.model.event.TemperatureSensorEvent;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.BaseEventAvroHandler;

@Component
@Qualifier("sensor")
public class TemperatureSensorEventAvroHandler extends BaseEventAvroHandler<TemperatureSensorAvro,TemperatureSensorEvent> {
    public TemperatureSensorEventAvroHandler(KafkaEventProducerAvro producer){
        super(producer);
    }

    @Override
    protected TemperatureSensorAvro mapToAvro(TemperatureSensorEvent event) {
        return TemperatureSensorAvro.newBuilder()
                .setTemperatureC(event.getTemperatureC())
                .setTemperatureF(event.getTemperatureF())
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }

    @Override
    protected SpecificRecordBase wrap(TemperatureSensorEvent event, TemperatureSensorAvro payload) {
        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }
}
