package thread.cas.spinlolck;

import static util.MyLogger.log;

public class SpinLockMain {

    public static void main(String[] args) {
        SpinLockBad spinLockBad = new SpinLockBad();

        Runnable task = () -> {
            spinLockBad.lock();

            try {
                // critical section
                log("비즈니스 로직 실행");
            } finally {
                spinLockBad.unlock();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
