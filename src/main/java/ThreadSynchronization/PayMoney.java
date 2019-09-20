package ThreadSynchronization;

import javax.sql.rowset.spi.TransactionalWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PayMoney {
    public static void main(String[] args) throws InterruptedException {
        Account a1=new Account("张三",10000);
        Account a2=new Account("李四",10000);
        ExecutorService pool= Executors.newFixedThreadPool(10);
        pool.execute(new WithDrawal(a1,a2,500));
        Thread.sleep(1000);
        a1.print();
        a2.print();
    }

    static class Account{
        String name;
        int money;
        Account(String name,int money){
            this.name=name;
            this.money=money;
        }
        synchronized boolean add(int num){
            if(num<0) return false;
            money+=num;
            return true;
        }
        synchronized boolean del(int num){
            if(num <0 || num>money) return false;
            money-=num;
            return true;
        }

        void print(){
            System.out.println(name + " has ￥"+money);
        }
    }

    static class WithDrawal implements Runnable{
        Account src,target;
        int num;
        WithDrawal(Account src,Account target,int num){
            this.src=src;
            this.target=target;
            this.num=num;
        }

        @Override
        public void run() {
            //开启事务
            if(src.del(num)){
                if(target.add(num)){
                    System.out.println("转账成功");
                }else{
                    //rollback
                    System.out.println("转账失败");
                }
            }else{
                System.out.println("转账失败");
            }
        }
    }
}
