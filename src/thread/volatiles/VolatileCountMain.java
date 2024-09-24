package thread.volatiles;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.flag);
        thread.start();

        sleep(1000);
        task.flag = false;
        log("flag = " + task.flag + ", count = " + task.count + " in main");

        log("main 종료");
    }

    static class MyTask implements Runnable {

//        boolean flag = true;
//        long count;
        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            log("task 시작");

            while (flag) {
                count ++;
                // 1억번에 한번씩 출력
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }

            log("flag = " + flag + ", count = " + count + " 종료");
        }


    }
}
