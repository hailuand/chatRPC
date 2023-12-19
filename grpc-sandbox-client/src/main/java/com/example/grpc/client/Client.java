package com.example.grpc.client;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.HelloRequest;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        var channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        var stub = GreetingServiceGrpc.newBlockingStub(channel);
        var response = stub.greeting(HelloRequest.newBuilder()
                        .setFirstname("Andreas")
                        .setLastname("Hailu")
                        .addAllHobbies(List.of("Hiking", "Reading Manga", "Cooking"))
                        .putBagOfTricks("Live coding", "Needs improvement...")
                .build());
        System.out.println(response);
    }
}
