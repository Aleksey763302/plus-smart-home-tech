package ru.yandex.practicum.telemetry.collector.service;

public interface EventSerializer <T>{
    void send(T message);
}
