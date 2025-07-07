package ru.yandex.practicum.telemetry.collector.service.proto.handler;

import com.google.protobuf.MessageLite;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.mapper.HubEventProtoToAvroMapper;
import ru.yandex.practicum.telemetry.collector.service.mapper.SensorEventProtoToAvroMapper;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;

public abstract class BaseEventProtoHandler<T extends MessageLite> {
    protected final KafkaEventProducerProto producerProto;

    protected final KafkaEventProducerAvro producerAvro;
    protected final HubEventProtoToAvroMapper hubMapper;
    protected final SensorEventProtoToAvroMapper sensorMapper;

    protected BaseEventProtoHandler(KafkaEventProducerProto producerProto, KafkaEventProducerAvro producerAvro) {
        this.producerProto = producerProto;
        this.producerAvro = producerAvro;
        this.hubMapper =  new HubEventProtoToAvroMapper();
        this.sensorMapper = new SensorEventProtoToAvroMapper();
    }

    public abstract Enum<?> getMessageType();

    public abstract void handle(T event);
}
