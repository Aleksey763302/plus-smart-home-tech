package ru.yandex.practicum.telemetry.collector.model.scenario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;

@Getter
@Setter
@ToString
public abstract class ScenarioEvent extends BaseEvent {
    @Max(2147483647)
    @Min(3)
    private String name;

    protected ScenarioEvent() {
        super();
    }

    @Override
    public abstract DeviceActionType getType();

}
