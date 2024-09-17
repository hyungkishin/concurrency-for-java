package thread.start.practice;

import static util.MyLogger.log;

public class StartTest1Main {

    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        counterThread.start();
    }

   static class CounterThread extends Thread {

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
