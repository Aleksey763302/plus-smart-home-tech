package ru.yandex.practicum.telemetry.collector.model.scenario;

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

    protected ScenarioAddedEvent(String hubId) {
        super(hubId);
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.SCENARIO_ADDED;
    }
}
