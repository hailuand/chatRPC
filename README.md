# 🤯🤯🤯

```
      _           _   ____  ____   ____ 
  ___| |__   __ _| |_|  _ \|  _ \ / ___|
 / __| '_ \ / _` | __| |_) | |_) | |    
| (__| | | | (_| | |_|  _ <|  __/| |___ 
 \___|_| |_|\__,_|\__|_| \_\_|    \____|
```

The next revolution of asynchronous messaging, chatRPC, is here!

## Objective 🧐
I built this to learn about the RPC protocol, and in particular, [gRPC](https://grpc.io).

Many enterprise services I've developed have all been exposed through a RESTful interface.
While sufficient, I've sometimes felt that the overhead of building a web server was quite a lot
for the problem at hand - particularly for a microservice-style architecture where services may
be quite chatty and not be user-facing.

## Running 👨🏾‍💻
Okay, this is obviously going to be the next killer unicorn app - how's it run?

1. Perform a `mvn package`
2. Run `com.example.grpc.chat.server.ChatServer` to bring up the gRPC server
3. Run as many `com.example.grpc.chat.client.ChatClient`s as you'd like to get to chatting!