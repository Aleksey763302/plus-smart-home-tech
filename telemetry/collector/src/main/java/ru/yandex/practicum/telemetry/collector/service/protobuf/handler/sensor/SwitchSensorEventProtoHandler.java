package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.SwitchSensorProto;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class SwitchSensorEventProtoHandler extends BaseProtobufEventHandler<SwitchSensorProto> {

    protected SwitchSensorEventProtoHandler(EventSerializer<SwitchSensorProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
