package ru.yandex.practicum.telemetry.collector.exception;

public class SerializationException extends RuntimeException {
    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
