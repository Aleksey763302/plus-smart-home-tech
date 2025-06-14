package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.model.event.TemperatureSensorEvent;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.AvroEventHandler;

@Component
public class TemperatureSensorEventAvroHandler extends BaseEventHandler<TemperatureSensorAvro, TemperatureSensorEvent> {

    public TemperatureSensorEventAvroHandler(EventSerializer<TemperatureSensorAvro> serializer) {
        super(serializer);
    }

    @Override
    protected TemperatureSensorAvro map(TemperatureSensorEvent event) {
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

}
