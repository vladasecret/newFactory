package threadPool;

public class ThreadPoolTask {
    private final Task task;

    public ThreadPoolTask(Task t) {
        task = t;
    }

    void go() throws InterruptedException {
        task.performWork();
    }
}
