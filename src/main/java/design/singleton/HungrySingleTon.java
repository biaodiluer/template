package design.singleton;

public class HungrySingleTon {
    private HungrySingleTon instance = new HungrySingleTon();

    private HungrySingleTon() {
    }

    public HungrySingleTon getInstance() {
        return instance;
    }
}
