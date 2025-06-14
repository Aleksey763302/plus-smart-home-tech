package ru.yandex.practicum.telemetry.collector.service.avro.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.model.event.SwitchSensorEvent;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;

@Component
public class SwitchSensorEventAvroHandler extends BaseEventHandler<SwitchSensorAvro, SwitchSensorEvent> {

    public SwitchSensorEventAvroHandler(EventSerializer<SwitchSensorAvro> serializer) {
        super(serializer);
    }

    @Override
    protected SwitchSensorAvro map(SwitchSensorEvent event) {
        return SwitchSensorAvro.newBuilder()
                .setState(event.isState())
                .build();
    }


    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
