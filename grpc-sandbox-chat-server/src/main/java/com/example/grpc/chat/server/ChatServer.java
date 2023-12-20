package com.example.grpc.chat.server;

import io.grpc.ServerBuilder;
import java.io.IOException;

public class ChatServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        var server =
                ServerBuilder.forPort(9090).addService(new ChatServiceImpl()).build();
        System.out.println("Starting chat server...");
        server.start();
        System.out.println("Server started.");
        server.awaitTermination();
    }
}
