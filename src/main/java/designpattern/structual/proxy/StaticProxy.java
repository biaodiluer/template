package designpattern.structual.proxy;

public class StaticProxy {

    public static void main(String[] args) {
        Subject s=new MyProxy(new Entity());
        s.function();
    }
}

class MyProxy implements Subject{
    private Entity entity;
    MyProxy(Entity entity){
        this.entity=entity;
    }
    /*
    直接调用被代理对象实现前置和后置
     */
    @Override
    public void function() {
        before();
        entity.function();
        after();
    }
    private void before(){
        System.out.println("代理类的前置");
    }
    private void after(){
        System.out.println("代理类的后置");
    }
}
