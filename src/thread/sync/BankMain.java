package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
        BankAccountV6 account = new BankAccountV6(1000);

        Thread t1 = new Thread(new WithDrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithDrawTask(account, 800), "t2");

        sleep(500);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log("최종 잔액: " + account.getBalance());
    }

}
