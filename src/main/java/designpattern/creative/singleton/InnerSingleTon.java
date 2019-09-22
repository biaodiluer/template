package designpattern.creative.singleton;


/*
单例模式：
1、构造函数私有化
2、提供getInstance接口
3、单例私有化
 */
public class InnerSingleTon {
    private InnerSingleTon() {
    }

    public static InnerSingleTon getInstance() {
        return SingleTonHolder.instance;
    }

    private static final class SingleTonHolder {
        private static InnerSingleTon instance = new InnerSingleTon();
    }

}
