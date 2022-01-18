package com.app1c.chat;

import com.app1c.chat.websocket.client.MyStompSessionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ChatApplication implements CommandLineRunner {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length != 0) {
            if (args[0].equals("client")) {
                System.out.println("----------------------------------");
                System.out.println("Welcome! You are on simple client!");
                System.out.println("----------------------------------");
                runUserInterface();
                return;
            }
        }
        SpringApplication.run(ChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------");
        System.out.println("Welcome! You are on master client!");
        System.out.println("----------------------------------");
        runUserInterface();
    }

    public static void runUserInterface() throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your username: ");
        String username = scanner.nextLine();
        StompSession stompSession = null;
        try {
            stompSession = initializeSession();
        } catch (Exception e) {
            System.out.println("-------------------------------");
            System.out.println("Failed to initialize session");
            System.out.println("-------------------------------");
            System.out.println("Check if master client is ready");
            System.out.println("-------------------------------");
            return;
        }


        ChatClient chatClient = new ChatClient(stompSession, username);

        String text;
        while (true) {
            text = scanner.nextLine();
            chatClient.sendMessage(text);
        }
    }

    public static StompSession initializeSession() throws ExecutionException, InterruptedException {
        String URL = "ws://localhost:8080/chat";
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        MyStompSessionHandler sessionHandler = new MyStompSessionHandler();
        return stompClient.connect(URL, sessionHandler).get();
    }
}