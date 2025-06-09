package ru.yandex.practicum.telemetry.collector.model.scenario;

import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;

public class ScenarioRemovedEvent extends ScenarioEvent{
    protected ScenarioRemovedEvent(String hubId) {
        super(hubId);
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.SCENARIO_REMOVED;
    }
}
