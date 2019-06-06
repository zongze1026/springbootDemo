package com.zongze.bigdata.io.echo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Create By xzz on 2019/5/31
 */
public class MySocketServer {


    public static void main(String[] args) {
        int coreThreadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newScheduledThreadPool(coreThreadCount);
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new SocketWorker(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
