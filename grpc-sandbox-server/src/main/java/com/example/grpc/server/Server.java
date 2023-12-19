package com.example.grpc.server;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        var server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl())
                .build();
        System.out.println("Starting gRPC server...");
        server.start();
        System.out.println("Server started.");
        server.awaitTermination();
    }
}
