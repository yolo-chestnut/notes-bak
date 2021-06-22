package pirv.yolo.threadexception.handler;

public class DefaultThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String threadName = Thread.currentThread().getName();
        System.out.println("方法1-设置默认全局异常处理，捕获异常：" + threadName + " 异常信息为：" + e);
    }
}
