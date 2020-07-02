package threadPool;

public interface Task {
    void performWork() throws InterruptedException;
}
