package ru.yandex.practicum.telemetry.collector.model.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public abstract class BaseEvent {
    @NotBlank
    private final String hubId;
    private final Instant timestamp;

    protected BaseEvent(String hubId){
        this.hubId = hubId;
        this.timestamp = Instant.now();
    }
    @NotNull
    public abstract Enum<?> getType();
}
