package thread.cas.increment;

public class VolatileInteger implements IncrementInteger {

    // volatile 은 여러 CPU 사이에 발생하는 캐시 메모리와 메인 메모리가 동기화 되지 않는 문제를 해결할 뿐
    // volatile 을 사용하면 CPU 의 캐시 메모리를 무시하고, 메인 메모리를 직접 사용해도 여전히 발생하는 문제이다.
    volatile private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }

}