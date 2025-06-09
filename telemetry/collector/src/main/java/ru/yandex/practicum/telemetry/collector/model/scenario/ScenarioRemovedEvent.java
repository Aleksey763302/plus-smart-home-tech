package ru.yandex.practicum.telemetry.collector.model.scenario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioRemovedEvent extends ScenarioEvent{
    public ScenarioRemovedEvent(String hubId) {
        super(hubId);
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.SCENARIO_REMOVED;
    }
}
