package thread.collection.simple;

import static util.MyLogger.log;

import thread.collection.simple.list.BasicList;
import thread.collection.simple.list.SimpleList;
import thread.collection.simple.list.SyncList;
import thread.collection.simple.list.SyncProxyList;

public class SimpleListMainV4 {

  public static void main(String[] args) throws InterruptedException {
    test(new SyncProxyList(new BasicList()));
  }

  private static void test(SimpleList list) throws InterruptedException {
    log(list.getClass().getSimpleName());

    Runnable addA = new Runnable() {
      @Override
      public void run() {
        list.add("A");
        log("Thread-1: list.add(A)");
      }
    };

    // B를 리스트에 저장하는 코드
    Runnable addB = new Runnable() {
      @Override
      public void run() {
        list.add("B");
        log("Thread-1: list.add(B)");
      }
    };

    Thread t1 = new Thread(addA, "Thread-1");
    Thread t2 = new Thread(addB, "Thread-2");
    t1.start();
    t2.start();

    t1.join();
    t2.join();

    log(list);
  }

}
