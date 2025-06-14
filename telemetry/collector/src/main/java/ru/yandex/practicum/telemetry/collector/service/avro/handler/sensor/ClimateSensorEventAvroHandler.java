package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import ru.yandex.practicum.telemetry.collector.model.event.ClimateSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;

@Component
public class ClimateSensorEventAvroHandler extends BaseEventHandler<ClimateSensorAvro, ClimateSensorEvent> {

    public ClimateSensorEventAvroHandler(EventSerializer<ClimateSensorAvro> serializer) {
        super(serializer);
    }

    @Override
    protected ClimateSensorAvro map(ClimateSensorEvent event) {
        return ClimateSensorAvro.newBuilder()
                .setCo2Level(event.getCo2Level())
                .setHumidity(event.getHumidity())
                .setTemperatureC(event.getTemperatureC())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
