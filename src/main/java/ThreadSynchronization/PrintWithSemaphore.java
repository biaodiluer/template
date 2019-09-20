package ThreadSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PrintWithSemaphore {

    /*
    semaphore最方便，不用判断num%3==threadId，而且初始状态很好设置第一个设为1就行
     */

    public static void main(String[] args) {
        Semaphore semaphore0=new Semaphore(1);
        Semaphore semaphore1=new Semaphore(0);
        Semaphore semaphore2=new Semaphore(0);
        ExecutorService pool= Executors.newFixedThreadPool(3);
        InnerTask2 t0= new InnerTask2(semaphore0,semaphore1,0);
        InnerTask2 t1= new InnerTask2(semaphore1,semaphore2,1);
        InnerTask2 t2= new InnerTask2(semaphore2,semaphore0,2);
        pool.execute(t0);
        pool.execute(t1);
        pool.execute(t2);
    }

    static class InnerTask2 implements Runnable{

        private static int threadNum=0;
        private Semaphore mySemaphore;
        private Semaphore nextSemaphore;
        private int threadId;
        private static int num=0;
        private static int end=100;

        InnerTask2(Semaphore mySemaphore,Semaphore nextSemaphore,int threadId){
            threadNum++;
            this.mySemaphore=mySemaphore;
            this.nextSemaphore=nextSemaphore;
            this.threadId=threadId;
        }

        @Override
        public void run() {
            while(num<=end){
                try {
                    mySemaphore.acquire();
                    if(num<=end)
                        System.out.println("thread " + threadId + " print " + num++);
                    nextSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
