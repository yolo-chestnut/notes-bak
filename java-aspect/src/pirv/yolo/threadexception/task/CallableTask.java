package pirv.yolo.threadexception.task;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {
    private final int i;

    public CallableTask(int i) {
        this.i = i;
    }

    @Override
    public String call() {
        String threadName = Thread.currentThread().getName();
        if (i == 10) {
            throw new RuntimeException(threadName + " 抛出一个异常！");
        }
        return threadName + "，success";
    }
}
