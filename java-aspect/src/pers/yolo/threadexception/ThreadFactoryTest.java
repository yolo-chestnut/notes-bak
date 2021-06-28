package pers.yolo.threadexception;

import pers.yolo.threadexception.factory.ExceptionFactory;
import pers.yolo.threadexception.handler.DefaultThreadExceptionHandler;
import pers.yolo.threadexception.task.CallableTask;
import pers.yolo.threadexception.task.Task;

import java.util.concurrent.*;

public class ThreadFactoryTest {
    public static void main(String[] args) throws InterruptedException {
        // 设置默认全局异常处理
        Thread.setDefaultUncaughtExceptionHandler(new DefaultThreadExceptionHandler());
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 5; i < 10; i++) {
            es.execute(new Task(i));
        }
        es.shutdown();

        Thread.sleep(1000);
        System.out.println("对比------");

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                5, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)
        );
        tpe.setThreadFactory(new ExceptionFactory());
        for (int i = 5; i < 10; i++) {
            tpe.execute(new Task(i));
        }
        // Callable类型线程任务
        Future<String> future = tpe.submit(new CallableTask(10));
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("线程池callable类型任务，捕获异常：" + e);
        }
        tpe.shutdown();
    }
}
