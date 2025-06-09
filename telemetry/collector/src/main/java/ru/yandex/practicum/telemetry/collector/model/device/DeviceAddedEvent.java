package ru.yandex.practicum.telemetry.collector.model.device;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAddedEvent extends DeviceEvent {

    private DeviceType deviceType;

    protected DeviceAddedEvent(String hubId) {
        super(hubId);
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.DEVICE_ADDED;
    }
}
