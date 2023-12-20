package com.example.grpc.chat.server;

import com.example.grpc.chat.Chat;
import com.example.grpc.chat.ChatServiceGrpc;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import java.util.LinkedHashSet;
import java.util.Set;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
    private static Set<StreamObserver<Chat.ChatMessageFromServer>> obs = new LinkedHashSet<>();

    @Override
    public StreamObserver<Chat.ChatMessage> chat(StreamObserver<Chat.ChatMessageFromServer> responseObserver) {
        obs.add(responseObserver);

        return new StreamObserver<>() {
            @Override
            public void onNext(Chat.ChatMessage chatMessage) {
                // When the client sends something to the server.
                var msg = Chat.ChatMessageFromServer.newBuilder()
                        .setTimestamp(Timestamp.newBuilder()
                                .setSeconds(System.currentTimeMillis() / 1000L)
                                .build())
                        .setMessage(chatMessage)
                        .build();
                obs.forEach(o -> o.onNext(msg));
            }

            @Override
            public void onError(Throwable throwable) {
                // Remove from listeners.
                obs.remove(responseObserver);
            }

            @Override
            public void onCompleted() {
                // Remove from listeners.
                obs.remove(responseObserver);
            }
        };
    }
}
