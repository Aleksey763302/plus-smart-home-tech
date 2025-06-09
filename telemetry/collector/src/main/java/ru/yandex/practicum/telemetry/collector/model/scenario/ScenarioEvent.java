package ru.yandex.practicum.telemetry.collector.model.scenario;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceActionType;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceAddedEvent;
import ru.yandex.practicum.telemetry.collector.model.device.DeviceRemovedEvent;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = DeviceActionType.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeviceAddedEvent.class, name = "SCENARIO_ADDED"),
        @JsonSubTypes.Type(value = DeviceRemovedEvent.class, name = "SCENARIO_REMOVED")

})
@Getter
@Setter
@ToString
public abstract class ScenarioEvent extends BaseEvent {
    @Max(2147483647)
    @Min(3)
    private String name;

    protected ScenarioEvent() {
        super();
    }

    @Override
    public abstract DeviceActionType getType();

}
