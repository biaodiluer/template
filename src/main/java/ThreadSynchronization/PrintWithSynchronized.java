package ThreadSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintWithSynchronized {

    /*
    三个线程顺序打印0-100
    只能全部唤醒，没法指定，虽然也能有相同的效果
    必须要wait和notify才能不超过100
     */
    public static void main(String[] args) {
        Object lock=new Object();
        ExecutorService pool= Executors.newFixedThreadPool(3);
        InnerTask1 t0= new InnerTask1(lock,0);
        InnerTask1 t1= new InnerTask1(lock,1);
        InnerTask1 t2= new InnerTask1(lock,2);
        pool.execute(t0);
        pool.execute(t1);
        pool.execute(t2);
    }

    static class InnerTask1 implements Runnable{

        private static int threadNum=0;
        private Object lock;
        private int threadId;
        private static int num=0;
        private static int end=100;

        InnerTask1(Object lock,int threadId){
            threadNum++;
            this.lock=lock;
            this.threadId=threadId;
        }

        @Override
        public void run() {
            while(num<=end){
                synchronized (lock){
                    if(num % threadNum == threadId){
                        //轮到自己打印了
                        System.out.println("thread "+ threadId +" print "+num);
                        num++;
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.notifyAll();
                    }else{
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
