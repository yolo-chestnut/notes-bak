package pers.yolo.threadexception;

import pers.yolo.threadexception.handler.DefaultThreadExceptionHandler;
import pers.yolo.threadexception.task.CallableTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest {
    public static void main(String[] args) {
        // 无效演示 不管是设置线程全局默认还是设置线程自己的异常处理，均无法捕获到callable类型抛出的异常
        Thread.setDefaultUncaughtExceptionHandler(new DefaultThreadExceptionHandler());
        FutureTask<String> futureTask = new FutureTask<>(new CallableTask(10));
        Thread thread = new Thread(futureTask, "callable");
        thread.setUncaughtExceptionHandler(
                (t, e) -> System.out.println(
                        "方法2-设置线程自己的异常处理，捕获异常：" + Thread.currentThread().getName() + " 异常信息为：" + e
                )
        );
        thread.start();

        // 正确异常处理
        try {
            String res = futureTask.get();
            System.out.println(res);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("捕获到callable类型异常：" + e);
        }
    }
}
