package ru.yandex.practicum.telemetry.collector.service.proto.handler.sensor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

@Component
@Qualifier("sensor")
public class SwitchSensorEventProtoHandler extends BaseEventProtoHandler<SensorEventProto> {
    public SwitchSensorEventProtoHandler(KafkaEventProducerAvro producerAvro) {
        super(producerAvro);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventProto.PayloadCase.SWITCH_SENSOR_EVENT;
    }


}
