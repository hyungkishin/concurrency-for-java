package thread.contol.yeild;

public class YieldMain {


    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < THREAD_COUNT; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
                // sleep(1); // 2. 내가 스케줄링 큐에서 빠져버린다. sleep RUNNABLE 상태가 TIMED_WAITING 으로 변경, 양보 할 스레드가 없는대도 양보할수 있다.
                Thread.yield(); // 3. cpu 에서 실행하는걸 빠져서 스케줄 큐에 다시 들어간다. Runnable 상태가 유지 + 양보 할 스레드가 없으면 본인 스레드가 계속 실행될수 있다.
            }
        }
    }

}
