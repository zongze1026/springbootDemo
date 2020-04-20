package com.zongze.nio;

import java.util.Scanner;
import java.util.UUID;

/**
 * Create By xzz on 2020/4/20
 */
public class ClientTest {

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        String clientName = UUID.randomUUID().toString().replaceAll("-", "");

        new Thread() {
            @Override
            public void run() {
                client.read();
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            client.send("【" + clientName + "】说: " + scanner.nextLine());
        }


    }


}
