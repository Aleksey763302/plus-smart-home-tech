package ru.yandex.practicum.telemetry.collector.model.device;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAddedEvent extends DeviceEvent {

    private DeviceType deviceType;

    public DeviceAddedEvent() {
        super();
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.DEVICE_ADDED;
    }
}
