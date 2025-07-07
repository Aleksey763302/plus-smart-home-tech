package ru.yandex.practicum.telemetry.collector.service.proto.mapper;
import com.google.protobuf.MessageLite;
import org.apache.avro.specific.SpecificRecordBase;

public interface ProtoToAvroMapper<T extends MessageLite, E extends SpecificRecordBase> {
    E map(T proto);
}
