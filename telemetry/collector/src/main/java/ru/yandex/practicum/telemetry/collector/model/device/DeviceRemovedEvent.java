package ru.yandex.practicum.telemetry.collector.model.device;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceRemovedEvent extends DeviceEvent{
    public DeviceRemovedEvent(String hubId) {
        super(hubId);
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.DEVICE_REMOVED;
    }
}
