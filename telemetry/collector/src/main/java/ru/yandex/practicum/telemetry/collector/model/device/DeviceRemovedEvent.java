package ru.yandex.practicum.telemetry.collector.model.device;

public class DeviceRemovedEvent extends DeviceEvent{
    protected DeviceRemovedEvent(String hubId) {
        super(hubId);
    }

    @Override
    public DeviceActionType getType() {
        return DeviceActionType.DEVICE_REMOVED;
    }
}
