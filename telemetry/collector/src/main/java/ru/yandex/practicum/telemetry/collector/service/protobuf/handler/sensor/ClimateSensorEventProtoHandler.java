package ru.yandex.practicum.telemetry.collector.service.protobuf.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.ClimateSensorProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioRemovedEventProto;
import ru.yandex.practicum.telemetry.collector.model.event.SensorEventType;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.BaseProtobufEventHandler;

@Component
public class ClimateSensorEventProtoHandler extends BaseProtobufEventHandler<ClimateSensorProto> {

    protected ClimateSensorEventProtoHandler(EventSerializer<ClimateSensorProto> serializer) {
        super(serializer);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
