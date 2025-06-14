package ru.yandex.practicum.telemetry.collector.controller;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.yandex.practicum.grpc.telemetry.collector.CollectorControllerGrpc;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.telemetry.collector.service.protobuf.handler.ProtobufEventHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Validated
@GrpcService
public class CollectorController extends CollectorControllerGrpc.CollectorControllerImplBase {
    private final Map<Enum<?>, ProtobufEventHandler<SensorEventProto>> eventHandlerMap;

    public CollectorController(
            List<ProtobufEventHandler<SensorEventProto>> sensorHandlers
    ) {
        this.eventHandlerMap = sensorHandlers.stream()
                .collect(Collectors.toMap(
                        ProtobufEventHandler::getMessageType,
                        Function.identity()
                ));
    }

    @Override
    public void collectSensorEvent(SensorEventProto request, StreamObserver<Empty> responseObserver){
        try {
            if (eventHandlerMap.containsKey(request.getPayloadCase())) {
                eventHandlerMap.get(request.getPayloadCase()).handle(request);
            } else {
                throw new IllegalArgumentException("Не могу найти обработчик для события " + request.getPayloadCase());
            }

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.fromThrowable(e)));
        }
    }
}
