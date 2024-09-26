package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

    private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    // 임계영역인 부분인데 synchronized 키워드를 붙이게 되면 한번에 하나의 쓰레드만 실행할 수 있게 된다.
    // 다른 쓰레드는 실행하지 못하고 기다리게 되는것이 핵심이다.
    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        // == 임계 영역 시작 ==
        log("[검증 시작] 출급액: " + amount + ", 잔액: " + balance);

        // 잔고가 출금액 보다 적으면, 진행하면 안됨
        if (balance < amount) {
            log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
            return false;
        }

        // 잔고가 출금액 보다 많으면, 진행
        log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000); // 출금에 걸리는 시간으로 가정
        balance = balance - amount;
        log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
        // == 임계 영역 종료 ==

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
