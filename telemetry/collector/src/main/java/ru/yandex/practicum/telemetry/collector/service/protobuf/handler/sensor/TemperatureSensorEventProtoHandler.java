package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.TemperatureSensorProto;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class TemperatureSensorEventProtoHandler extends BaseProtobufEventHandler<TemperatureSensorProto> {

    protected TemperatureSensorEventProtoHandler(EventSerializer<TemperatureSensorProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }

}
