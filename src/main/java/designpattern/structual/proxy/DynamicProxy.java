package designpattern.structual.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Subject s=new Entity();
        Subject proxy= (Subject) Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new MyProxyHandler(s)
        );
        proxy.function();
    }
}

class MyProxyHandler implements InvocationHandler{
    private Object entity;

    public MyProxyHandler(Object entity){
        this.entity=entity;
    }

    /*
    通过反射方式
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object ans=method.invoke(entity,args);
        after();
        return ans;
    }

    private void before(){
        System.out.println("代理类的前置");
    }
    private void after(){
        System.out.println("代理类的后置");
    }
}

