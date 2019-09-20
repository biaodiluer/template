package ThreadSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintWithCondition {

    /*
    只用lock的话和没有wait notify的syn是一样的
    用lock和一个condition和用了wait notify的syn是一样的
    用多个condition就可以严格控制唤醒特定线程，但初始唤醒线程不好弄
     */

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition0 = lock.newCondition();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        InnerTask2 t0 = new InnerTask2(lock, condition0, condition1, 0);
        InnerTask2 t1 = new InnerTask2(lock, condition1, condition2, 1);
        InnerTask2 t2 = new InnerTask2(lock, condition2, condition0, 2);
        pool.execute(t0);
        pool.execute(t1);
        pool.execute(t2);
        Thread.sleep(1000);
        lock.lock();
        condition0.signal();
        lock.unlock();
    }

    static class InnerTask2 implements Runnable {

        private static int threadNum = 0;
        private static ReentrantLock lock;
        private static int num = 0;
        private static int end = 100;
        private Condition myCondition;
        private Condition nextCondition;
        private int threadId;

        InnerTask2(ReentrantLock lock, Condition myCondition, Condition nextCondition, int threadId) {
            threadNum++;
            this.lock = lock;
            this.myCondition = myCondition;
            this.nextCondition = nextCondition;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (num <= end) {
                lock.lock();
                try {
                    myCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread " + threadId + " print " + num++);
                nextCondition.signal();
                lock.unlock();
            }
        }
    }
}
