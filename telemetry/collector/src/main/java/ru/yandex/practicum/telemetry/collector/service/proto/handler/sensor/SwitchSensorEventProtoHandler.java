package ru.yandex.practicum.telemetry.collector.service.proto.handler.sensor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

@Component
@Qualifier("sensor")
public class SwitchSensorEventProtoHandler extends BaseEventProtoHandler<SensorEventProto> {
    public SwitchSensorEventProtoHandler(KafkaEventProducerProto producer) {
        super(producer);
    }

    @Override
    public Enum<?> getMessageType() {
        return SensorEventProto.PayloadCase.SWITCH_SENSOR_EVENT;
    }

    @Override
    public void handle(SensorEventProto event) {
        producer.send(event);
    }


}
