package ru.yandex.practicum.telemetry.collector.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;
import ru.yandex.practicum.telemetry.collector.service.avro.handler.AvroEventSerializer;

@Configuration
public class AvroSerializerConfig {
    private final AvroEventSerializer serializer;

    public AvroSerializerConfig(AvroEventSerializer serializer) {
        this.serializer = serializer;
    }

    @Bean
    public EventSerializer<DeviceAddedEventAvro> deviceAddedEventSerializer() {
        return serializer::send;
    }

    @Bean
    public EventSerializer<DeviceRemovedEventAvro> deviceRemovedEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<ScenarioAddedEventAvro> scenarioAddedEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<ScenarioRemovedEventAvro> scenarioRemovedEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<ClimateSensorAvro> climateSensorEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<LightSensorAvro> lightSensorEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<MotionSensorAvro> motionSensorEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<SwitchSensorAvro> switchSensorEventSerializer(){
        return serializer::send;
    }

    @Bean
    public EventSerializer<TemperatureSensorAvro> temperatureSensorEventSerializer(){
        return serializer::send;
    }

}
