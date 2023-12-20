package com.example.grpc.server;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println(request);
        responseObserver.onNext(HelloResponse.newBuilder()
                .setGreeting("Hello, " + request.getFirstname() + "!")
                .build());
        responseObserver.onCompleted();
    }
}
