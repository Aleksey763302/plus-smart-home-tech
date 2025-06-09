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
    private String hubId;
    private final Instant timestamp;

    protected BaseEvent(){
        this.timestamp = Instant.now();
    }
    @NotNull
    public abstract Enum<?> getType();
}
