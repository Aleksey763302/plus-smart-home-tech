package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.MotionSensorProto;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class MotionSensorEventProtoHandler extends BaseProtobufEventHandler<MotionSensorProto> {

    protected MotionSensorEventProtoHandler(EventSerializer<MotionSensorProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
