package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println("atomicInteger = " + atomicInteger.getClass().getSimpleName() + " resultValue: " + result);

    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;

        boolean result;

        do {
            getValue = atomicInteger.get(); // thread1: 0
            log("getValue: " + getValue);
//            sleep(100); // 스레드 동시 실행을 위한 대기
            //  thread2 value -> 1
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while (!result);

        // 다른 스레드가 값을 변경할 수 있다. -> atomicInteger.get();
        return getValue + 1;
    }

}
