package com.zongze.bigdata.io.echo;

import com.zongze.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Create By xzz on 2019/5/31
 */
public class MySocketServer {


    public static void main(String[] args) throws InterruptedException {
        int coreThreadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newScheduledThreadPool(coreThreadCount);
//        try {
//            ServerSocket serverSocket = new ServerSocket(8888);
//            while (true){
//                Socket clientSocket = serverSocket.accept();
//                executorService.submit(new SocketWorker(clientSocket));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<Callable<User>> tasks = new ArrayList<>();
        tasks.add(new ThreadPollTest(new User("张三1")));
        tasks.add(new ThreadPollTest(new User("张三2")));
        tasks.add(new ThreadPollTest(new User("张三3")));
        tasks.add(new ThreadPollTest(new User("张三4")));
        tasks.add(new ThreadPollTest(new User("张三5")));
        tasks.add(new ThreadPollTest(new User("张三6")));
        tasks.add(new ThreadPollTest(new User("张三7")));
        tasks.add(new ThreadPollTest(new User("张三8")));
        tasks.add(new ThreadPollTest(new User("张三9")));
        tasks.add(new ThreadPollTest(new User("张三10")));
        tasks.add(new ThreadPollTest(new User("张三11")));
        tasks.add(new ThreadPollTest(new User("张三12")));
        List<Future<User>> futures = executorService.invokeAll(tasks);
        futures.stream().forEach(future->{
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });


    }

    private static int getNumber() {
        int i = 0;
        for (;;) {
            System.out.println("线程执行中");
            if (i == 5) {
                return i;
            }
            i++;
        }
    }


}
