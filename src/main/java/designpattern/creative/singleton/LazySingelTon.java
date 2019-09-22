package designpattern.creative.singleton;

public class LazySingelTon {
    //一定要是volatile保证指令有序
    private volatile LazySingelTon instance;

    private LazySingelTon() {
    }

    public LazySingelTon getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new LazySingelTon();
                }
            }
        }
        return instance;
    }
}
