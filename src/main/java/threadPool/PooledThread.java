package threadPool;

import java.util.LinkedList;

public class PooledThread extends Thread {
    private final LinkedList<ThreadPoolTask> taskQueue;

    public PooledThread(String name, LinkedList<ThreadPoolTask> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    private void performTask(ThreadPoolTask t) throws InterruptedException {
            t.go();
    }

    public void run() {
        ThreadPoolTask toExecute;
        while (!isInterrupted()) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException ex) {
                        return;
                    }
                    continue;
                } else {
                    toExecute = taskQueue.remove(0);
                }
            }
            try {
                performTask(toExecute);
            } catch (InterruptedException exception) {
                break;
            }
        }
    }
}
