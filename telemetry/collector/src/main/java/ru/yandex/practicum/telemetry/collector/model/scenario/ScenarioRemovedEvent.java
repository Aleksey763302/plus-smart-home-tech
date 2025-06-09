package ru.yandex.practicum.telemetry.collector.model.scenario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioRemovedEvent extends ScenarioEvent{

    public ScenarioRemovedEvent() {
        super();
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.SCENARIO_REMOVED;
    }
}
