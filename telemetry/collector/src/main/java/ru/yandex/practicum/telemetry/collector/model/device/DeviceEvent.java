package ru.yandex.practicum.telemetry.collector.model.device;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class DeviceEvent extends BaseEvent{
    @NotBlank
    private String id;

    protected DeviceEvent() {
        super();
    }

    @Override
    public abstract DeviceActionType getType();
}
