package design.singleton;

/*
类似于内部类的单例模式，内部的类或者枚举只是中间跳板，真正被用到的类是外面的类
1、构造函数私有化
2、提供getInstance接口
 */
public class EnumSingleTon {
    private EnumSingleTon(){}
    public static EnumSingleTon getInstance(){
        return SingleTon.INSTANCE.getInstance();
    }
    private enum SingleTon{
        INSTANCE;
        private EnumSingleTon instance;
        SingleTon(){
            instance=new EnumSingleTon();
        }
        public EnumSingleTon getInstance(){return instance;}
    }
}

class Main{
    public static void main(String[] args) {
        EnumSingleTon e1=EnumSingleTon.getInstance();
        EnumSingleTon e2=EnumSingleTon.getInstance();
        System.out.println(e1);
        System.out.println(e2);
    }
}
