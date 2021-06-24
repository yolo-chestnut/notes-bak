package pers.yolo.threadexception.threadgroup;

public class ThreadGroup3 extends ThreadGroup {
    public ThreadGroup3(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//        super.uncaughtException(t, e);
        String threadName = Thread.currentThread().getName();
        System.out.println("方法3-设置线程组的异常处理，捕获异常：" + threadName + " 异常信息为：" + e);
    }
}
