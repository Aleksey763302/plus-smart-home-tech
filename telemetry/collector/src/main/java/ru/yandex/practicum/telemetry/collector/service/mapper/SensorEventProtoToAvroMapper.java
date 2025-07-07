package ru.yandex.practicum.telemetry.collector.service.mapper;

import ru.yandex.practicum.grpc.telemetry.event.*;
import ru.yandex.practicum.kafka.telemetry.event.*;

public class SensorEventProtoToAvroMapper implements ProtoToAvroMapper<SensorEventProto, SensorEventAvro>{
    @Override
    public SensorEventAvro map(SensorEventProto proto) {
        Object payloadCase = switch (proto.getPayloadCase()){
            case CLIMATE_SENSOR_EVENT -> mapClimateSensor(proto.getClimateSensorEvent());
            case LIGHT_SENSOR_EVENT -> mapLightSensor(proto.getLightSensorEvent());
            case MOTION_SENSOR_EVENT -> mapMotionSensor(proto.getMotionSensorEvent());
            case SWITCH_SENSOR_EVENT -> mapSwitchSensor(proto.getSwitchSensorEvent());
            case TEMPERATURE_SENSOR_EVENT -> mapTemperatureSensor(proto.getTemperatureSensorEvent());
            default -> throw new IllegalArgumentException("Unknown payload type: " + proto.getPayloadCase());
        };
        return SensorEventAvro.newBuilder()
                .setId(proto.getId())
                .setHubId(proto.getHubId())
                .setTimestamp(java.time.Instant.ofEpochSecond(
                        proto.getTimestamp().getSeconds(),
                        proto.getTimestamp().getNanos()))
                .setPayload(payloadCase)
                .build();
    }

    private ClimateSensorAvro mapClimateSensor(ClimateSensorProto proto){
        return ClimateSensorAvro.newBuilder()
                .setCo2Level(proto.getCo2Level())
                .setHumidity(proto.getHumidity())
                .setTemperatureC(proto.getTemperatureC())
                .build();
    }

    private LightSensorAvro mapLightSensor(LightSensorProto proto){
        return LightSensorAvro.newBuilder()
                .setLinkQuality(proto.getLinkQuality())
                .setLuminosity(proto.getLuminosity())
                .build();
    }

    private MotionSensorAvro mapMotionSensor(MotionSensorProto proto){
        return MotionSensorAvro.newBuilder()
                .setMotion(proto.getMotion())
                .setVoltage(proto.getVoltage())
                .setLinkQuality(proto.getLinkQuality())
                .build();
    }

    private SwitchSensorAvro mapSwitchSensor(SwitchSensorProto proto){
        return SwitchSensorAvro.newBuilder()
                .setState(proto.getState())
                .build();
    }

    private TemperatureSensorAvro mapTemperatureSensor(TemperatureSensorProto proto){
        return TemperatureSensorAvro.newBuilder()
                .setTemperatureF(proto.getTemperatureF())
                .setTemperatureC(proto.getTemperatureC())
                .build();

    }
}
