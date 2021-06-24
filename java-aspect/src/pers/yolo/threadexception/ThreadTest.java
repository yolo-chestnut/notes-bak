package pers.yolo.threadexception;

import pers.yolo.threadexception.handler.DefaultThreadExceptionHandler;
import pers.yolo.threadexception.task.Task;
import pers.yolo.threadexception.threadgroup.ThreadGroup3;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i), "test-" + i).start();
        }

        // 保证上述程序运行完成，形成对比（线程test-5与method-1、2、3对比）
        Thread.sleep(10000);

        try {
            // 方法1
            Thread.setDefaultUncaughtExceptionHandler(new DefaultThreadExceptionHandler());
            new Thread(new Task(5), "method-1").start();

            // 方法2
            Thread thread2 = new Thread(new Task(5), "method-2");
            thread2.setUncaughtExceptionHandler(
                    (t, e) -> System.out.println(
                            "方法2-设置线程自己的异常处理，捕获异常：" + Thread.currentThread().getName() + " 异常信息为：" + e
                    )
            );
            thread2.start();

            // 方法3
            new Thread(new ThreadGroup3("group-3"), new Task(5), "method-3").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
