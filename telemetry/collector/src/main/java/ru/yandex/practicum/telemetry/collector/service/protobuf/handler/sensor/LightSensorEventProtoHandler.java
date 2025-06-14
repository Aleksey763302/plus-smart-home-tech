package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.LightSensorProto;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class LightSensorEventProtoHandler extends BaseProtobufEventHandler<LightSensorProto>{

    protected LightSensorEventProtoHandler(EventSerializer<LightSensorProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
