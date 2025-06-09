package ru.yandex.practicum.telemetry.collector.model.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TemperatureSensorEvent extends SensorEvent {
    private int temperatureC;
    private int temperatureF;

    protected TemperatureSensorEvent(String hubId) {
        super(hubId);
    }

    @Override
    public SensorEventType getType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }
}
