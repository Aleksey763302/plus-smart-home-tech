package ru.yandex.practicum.telemetry.collector.service.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.model.event.SwitchSensorEvent;
import ru.yandex.practicum.telemetry.collector.service.KafkaEventProducer;
import ru.yandex.practicum.telemetry.collector.service.handler.BaseEventHandler;

@Component
@Qualifier("sensor")
public class SwitchSensorEventHandler extends BaseEventHandler<SwitchSensorAvro,SwitchSensorEvent> {
    public SwitchSensorEventHandler(KafkaEventProducer producer){
        super(producer);
    }

    @Override
    protected SwitchSensorAvro mapToAvro(SwitchSensorEvent event) {
        return SwitchSensorAvro.newBuilder()
                .setState(event.isState())
                .build();
    }

    @Override
    protected SpecificRecordBase wrap(SwitchSensorEvent event, SwitchSensorAvro payload) {
        return SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setPayload(payload)
                .setTimestamp(event.getTimestamp())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
