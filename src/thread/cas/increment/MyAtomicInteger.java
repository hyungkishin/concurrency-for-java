package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger {

    /**
     * AtomicInteger 를 사용하면 멀티스레드 상황에 안전하고 또 다양한 값 증가, 감소 연산을 제공한다. 특정 값을 증가하거나 감소해야 하는데 여러 스레드가 해당 값을 공유해야 한다면, AtomicInteger 를 사용하면 된다.
     * */

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }

}