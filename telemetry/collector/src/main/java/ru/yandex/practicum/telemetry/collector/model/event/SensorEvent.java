package ru.yandex.practicum.telemetry.collector.model.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;

@Getter
@Setter
@ToString
public abstract class SensorEvent extends BaseEvent {
    @NotBlank
    private String id;

    protected SensorEvent() {
        super();
    }

    @Override
    public abstract SensorEventType getType();
}
