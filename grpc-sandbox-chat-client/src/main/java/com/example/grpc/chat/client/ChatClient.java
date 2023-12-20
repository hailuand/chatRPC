package com.example.grpc.chat.client;

import com.example.grpc.chat.Chat;
import com.example.grpc.chat.ChatServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.time.Instant;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        System.out.println(
                """
                      _           _   ____  ____   ____\s
                  ___| |__   __ _| |_|  _ \\|  _ \\ / ___|
                 / __| '_ \\ / _` | __| |_) | |_) | |   \s
                | (__| | | | (_| | |_|  _ <|  __/| |___\s
                 \\___|_| |_|\\__,_|\\__|_| \\_\\_|    \\____|
                """);
        var channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        var chatService = ChatServiceGrpc.newStub(channel);
        var chat = chatService.chat(new StreamObserver<>() {
            @Override
            public void onNext(Chat.ChatMessageFromServer chatMessageFromServer) {
                System.out.printf(
                        "%s %s: %s\n",
                        Instant.ofEpochSecond(
                                chatMessageFromServer.getTimestamp().getSeconds()),
                        chatMessageFromServer.getMessage().getFrom(),
                        chatMessageFromServer.getMessage().getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("Disconnected.");
            }

            @Override
            public void onCompleted() {
                System.out.println("Disconnected.");
            }
        });

        var scanner = new Scanner(System.in);
        System.out.println("What's your name?");
        var name = scanner.next();
        System.out.println("Great! Now you can get to chatting with chatRPC.");
        System.out.println("Start typing!");
        while (true) {
            if (scanner.hasNext()) {
                var msg = scanner.nextLine();
                chat.onNext(Chat.ChatMessage.newBuilder()
                        .setFrom(name)
                        .setMessage(msg)
                        .build());
            }
        }
    }
}
