package ru.yandex.practicum.telemetry.collector.model.scenario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceAction;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioAddedEvent extends ScenarioEvent{
    @NotNull
    private List<ScenarioCondition> conditions;
    @NotNull
    private List<DeviceAction> actions;

    public ScenarioAddedEvent() {
        super();
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.SCENARIO_ADDED;
    }
}
