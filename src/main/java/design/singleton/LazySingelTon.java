package design.singleton;

public class LazySingelTon {
    private LazySingelTon instance;

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
