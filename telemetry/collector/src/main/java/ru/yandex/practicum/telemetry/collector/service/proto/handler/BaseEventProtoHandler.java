package ru.yandex.practicum.telemetry.collector.service.proto.handler;

import com.google.protobuf.MessageLite;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.telemetry.collector.service.avro.KafkaEventProducerAvro;
import ru.yandex.practicum.telemetry.collector.service.proto.mapper.HubEventProtoToAvroMapper;
import ru.yandex.practicum.telemetry.collector.service.proto.mapper.ProtoToAvroMapper;
import ru.yandex.practicum.telemetry.collector.service.proto.mapper.SensorEventProtoToAvroMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseEventProtoHandler<T extends MessageLite> {
    protected final KafkaEventProducerAvro producerAvro;
    protected final Map<Class<? extends MessageLite>, ProtoToAvroMapper<? extends MessageLite, ? extends SpecificRecordBase>> mappers = new HashMap<>();

    protected BaseEventProtoHandler(KafkaEventProducerAvro producerAvro) {
        this.producerAvro = producerAvro;
        mappers.put(HubEventProto.class, new HubEventProtoToAvroMapper());
        mappers.put(SensorEventProto.class, new SensorEventProtoToAvroMapper());
    }

    public abstract Enum<?> getMessageType();

    @SuppressWarnings("unchecked")
    public void handle(T event){
        if (Objects.isNull(event)) throw new NullPointerException("Event is null");
        ProtoToAvroMapper<T, SpecificRecordBase> mapper =
                (ProtoToAvroMapper<T, SpecificRecordBase>) mappers.get(event.getClass());

        if (Objects.isNull(mapper)) {
            throw new IllegalStateException("No mapper found for: " + event.getClass().getName());
        }

        SpecificRecordBase avroEvent = mapper.map(event);
        producerAvro.send(avroEvent);
    }

}
