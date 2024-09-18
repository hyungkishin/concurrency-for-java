package thread.contol;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {

    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId=() = " + mainThread.threadId());
        log("mainThread.name = " + mainThread.getName());
        log("mainThread.getPriority() = " + mainThread.getPriority()); // 기본이 5 ( 우선순위가 높을수록 많이 실행됨 ) , 운영체제가 알아서 잘 조정해줌 사실 // 1 (가장 낮음) 10 (가장 높음)
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        log("mainThread.getState() = " + mainThread.getState());

        log("-------------------------------------");

        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("myThread = " + myThread);
        log("myThread.threadId=() = " + myThread.threadId());
        log("myThread.name = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority()); // 기본이 5 ( 우선순위가 높을수록 많이 실행됨 ) , 운영체제가 알아서 잘 조정해줌 사실
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("myThread.getState() = " + myThread.getState());

    }
}
