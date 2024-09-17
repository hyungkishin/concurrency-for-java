package thread.start.practice;

import static util.MyLogger.log;

public class StartTest2Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new CounterRunnable(), "counter");
        thread.start();
    }

   static class CounterRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                    log("value:" + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
