package ru.yandex.practicum.telemetry.collector.service.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.event.ClimateSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.KafkaEventProducer;
import ru.yandex.practicum.telemetry.collector.service.handler.BaseEventHandler;

@Component
@Qualifier("sensor")
public class ClimateSensorEventHandler extends BaseEventHandler<ClimateSensorAvro,ClimateSensorEvent> {
    public ClimateSensorEventHandler(KafkaEventProducer producer){
        super(producer);
    }


    @Override
    protected ClimateSensorAvro mapToAvro(ClimateSensorEvent event) {
        return ClimateSensorAvro.newBuilder()
                .setCo2Level(event.getCo2Level())
                .setHumidity(event.getHumidity())
                .setTemperatureC(event.getTemperatureC())
                .build();
    }

    @Override
    protected SpecificRecordBase wrap(ClimateSensorEvent event, ClimateSensorAvro payload) {
        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
