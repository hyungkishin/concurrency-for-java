package thread.cas.increment;

public class SyncInteger implements IncrementInteger {

    private int value;

    // 역시 synchronized 키워드를 사용하면, 임계 영역을 만들어 쓰레드 하나씩 연산을 실행하여 기대한 연산이 이루어진다.

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }

}