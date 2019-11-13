package com.zongze.util;

import java.util.concurrent.*;

/**
 * Create By xzz on 2019/11/11
 */
public class ThreadPool {
    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(50000);
    private static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
    private static ThreadPoolExecutor executorService;

    static {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 500, 10000l, TimeUnit.MILLISECONDS, workQueue, handler);
    }


    public static void shutdownThreadPool() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                executorService.shutdown();
                try {
                    if (executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS)) {
                        executorService.shutdownNow();
                        System.out.println("tui");
                    }
                } catch (InterruptedException e) {
                    executorService.shutdownNow();
                    e.printStackTrace();
                }
            }
        });

    }


    public static Future execute(Runnable task) {
        return executorService.submit(task);
    }

    public static <T> T execute(Callable<T> task) throws ExecutionException, InterruptedException {
        Future<T> future = executorService.submit(task);
        return future.get();
    }

    public static String monitor() {
        return executorService.toString();
    }


}
