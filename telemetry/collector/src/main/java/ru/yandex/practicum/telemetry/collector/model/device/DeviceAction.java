package ru.yandex.practicum.telemetry.collector.model.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceAction {
    @NotBlank
    private String sensorId;
    @NotNull
    private ActionType type;
    private Integer value;
}
