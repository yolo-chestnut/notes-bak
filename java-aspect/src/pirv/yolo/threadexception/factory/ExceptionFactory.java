package pirv.yolo.threadexception.factory;

import java.util.concurrent.ThreadFactory;

public class ExceptionFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        assert r != null;
        Thread thread = new Thread(r);
        // 重写线程工厂中生成线程方法，并为线程设置异常处理
        thread.setUncaughtExceptionHandler(
                (t, e) -> System.out.println("线程工厂中设置捕获线程异常，具体异常为：" + e)
        );
        return thread;
    }
}
