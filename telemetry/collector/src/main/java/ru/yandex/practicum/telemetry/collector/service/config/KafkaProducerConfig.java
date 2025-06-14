package ru.yandex.practicum.telemetry.collector.service.config;

import com.google.protobuf.MessageLite;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.yandex.practicum.kafka.serializer.GeneralAvroSerializer;
import ru.yandex.practicum.kafka.serializer.GeneralProtobufSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean("producerFactoryAvro")
    public ProducerFactory<String, SpecificRecordBase> producerFactoryAvro() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GeneralAvroSerializer.class.getName());

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean("kafkaTemplateAvro")
    public KafkaTemplate<String, SpecificRecordBase> kafkaTemplateAvro() {
        return new KafkaTemplate<>(producerFactoryAvro());
    }

    @Bean("producerFactoryProto")
    public ProducerFactory<String, MessageLite> producerFactoryProto() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GeneralProtobufSerializer.class.getName());

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean("kafkaTemplateProto")
    public KafkaTemplate<String, MessageLite> kafkaTemplateProto() {
        return new KafkaTemplate<>(producerFactoryProto());
    }
}
