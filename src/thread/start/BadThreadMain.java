package thread.start;

public class BadThreadMain {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + " main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start 호출 전");
        helloThread.run(); // main 이 method 호출한것이다. 별도의 Thread 가 실행한것이 아니다. - 메인이 자기 메서드 호출한 격.
        System.out.println(Thread.currentThread().getName() + ": start 호출 후");
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
