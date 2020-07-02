package threadPool;

import java.util.*;

public class ThreadPool  {
    private final LinkedList<ThreadPoolTask> taskQueue = new LinkedList<>();
    private final HashSet<PooledThread> availableThreads = new HashSet<>();
    public void addTask(Task t) {
        synchronized (taskQueue) {
            taskQueue.add(new ThreadPoolTask(t));
            taskQueue.notify();
        }
    }

    public ThreadPool(Integer noOfThreads) {

        for (int i = 0; i < noOfThreads; i++) {
            availableThreads.add(new PooledThread(Integer.toString(i), taskQueue));
        }
        for (var thread : availableThreads) {
            thread.start();
        }
    }

    public void stop() throws InterruptedException {
        for (PooledThread thread : availableThreads){
            thread.interrupt();
//            if (!thread.isInterrupted()){
//                thread.interrupt();
//            }
        }
        join();
    }

    public void join() throws InterruptedException {
        for (PooledThread thread : availableThreads){
            if (thread.isAlive()){
                thread.join();
            }
        }
    }
}
