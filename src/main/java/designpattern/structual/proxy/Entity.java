package designpattern.structual.proxy;

/*
被代理类
 */
public class Entity implements Subject{
    @Override
    public void function() {
        System.out.println("被代理类的方法");
    }
}
