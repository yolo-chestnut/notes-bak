package pers.yolo.threadexception.task;

public class Task implements Runnable {
    private final int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (i == 5) {
            throw new RuntimeException(threadName + " 抛出了一个异常！");
        }
        System.out.println(threadName + "：" + i);
    }
}
