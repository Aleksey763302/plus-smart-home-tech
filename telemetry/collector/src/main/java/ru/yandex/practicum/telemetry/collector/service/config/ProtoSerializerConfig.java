package ru.yandex.practicum.telemetry.collector.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.practicum.grpc.telemetry.event.*;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.ProtoEventSerializer;

@Configuration
public class ProtoSerializerConfig {
    private final ProtoEventSerializer serializer;

    public ProtoSerializerConfig(ProtoEventSerializer serializer) {
        this.serializer = serializer;
    }

    @Bean
    public EventSerializer<DeviceAddedEventProto> deviceAddedProtoSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<DeviceRemovedEventProto> deviceRemovedProtoSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<ScenarioAddedEventProto>scenarioAddedProtoSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<ScenarioRemovedEventProto>scenarioRemovedProtoSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<ClimateSensorProto>climateSensorProtoSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<LightSensorProto>lightSensorProtoSerializer(){
        return serializer::send;
    }
    @Bean
    public EventSerializer<MotionSensorProto>motionSensorProtoSerializer(){
        return serializer::send;
    }
    @Bean
    public EventSerializer<SwitchSensorProto>switchSensorProtoSerializer(){
        return serializer::send;
    }
    @Bean
    public EventSerializer<TemperatureSensorProto>temperatureSensorProtoSerializer(){
        return serializer::send;
    }
}
