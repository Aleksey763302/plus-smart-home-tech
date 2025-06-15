package ru.yandex.practicum.telemetry.collector.controller;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yandex.practicum.grpc.telemetry.collector.CollectorControllerGrpc;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.telemetry.collector.service.proto.handler.BaseEventProtoHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Validated
@GrpcService
public class CollectorController extends CollectorControllerGrpc.CollectorControllerImplBase{
    private final Map<Enum<?>, BaseEventProtoHandler<?>> sensorEventHandlerMap;
    private final Map<Enum<?>, BaseEventProtoHandler<?>> hubEventHandlerMap;

    public CollectorController(
            @Qualifier("sensor") List<BaseEventProtoHandler<?>> sensorHandlers,
            @Qualifier("hub") List<BaseEventProtoHandler<?>> hubHandlers
    ) {
        this.sensorEventHandlerMap = sensorHandlers.stream()
                .collect(Collectors.toMap(
                        BaseEventProtoHandler::getMessageType,
                        Function.identity()
                ));

        this.hubEventHandlerMap = hubHandlers.stream()
                .collect(Collectors.toMap(
                        BaseEventProtoHandler::getMessageType,
                        Function.identity()
                ));
        System.out.println("hubEventHandlerMap " + hubEventHandlerMap.size());
        System.out.println("sensorEventHandlerMap " + sensorEventHandlerMap.size());
    }

    @Override
    public void collectSensorEvent(SensorEventProto request, StreamObserver<Empty> responseObserver){
        try {
            Enum<?> type = request.getPayloadCase();
            BaseEventProtoHandler<?> handler = sensorEventHandlerMap.get(type);

            if (handler == null) {
                throw new IllegalArgumentException("Не могу найти обработчик для события " + type);
            }

            @SuppressWarnings("unchecked")
            BaseEventProtoHandler<SensorEventProto> typedHandler = (BaseEventProtoHandler<SensorEventProto>) handler;
            typedHandler.handle(request);

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.fromThrowable(e)));
        }
    }

    @Override
    public void collectHubEvent(HubEventProto request, StreamObserver<Empty> responseObserver){
        try {
            Enum<?> type = request.getPayloadCase();
            BaseEventProtoHandler<?> handler = hubEventHandlerMap.get(type);

            if (handler == null) {
                throw new IllegalArgumentException("Не могу найти обработчик для события " + type);
            }

            @SuppressWarnings("unchecked")
            BaseEventProtoHandler<HubEventProto> typedHandler = (BaseEventProtoHandler<HubEventProto>) handler;
            typedHandler.handle(request);

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.fromThrowable(e)));
        }
    }
}
