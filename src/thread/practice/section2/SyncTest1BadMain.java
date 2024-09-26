package thread.practice.section2;

/**
 * 문제 1 - 공유 자원
 * 다음 코드의 결과는 20000 이 되어야 한다.
 * 이 코드의 문제점을 찾아서 해결하라.
 * Counter 클래스 내부만 수정해야 한다.
 */
public class SyncTest1BadMain {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("결과: " + counter.getCount());
    }

    private static class Counter {

        private int count = 0;

        public synchronized void increment() {
            count = count + 1;
        }

        public int getCount() {
            return count;
        }
    }
}
