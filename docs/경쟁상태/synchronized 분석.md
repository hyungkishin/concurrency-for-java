### Synchronized 분석
- 모든 객체(인스턴스)는 내부에 락 (lock) 을 가지고 있다.
  - 이를 모니터 락 (monitor lock) 이라고 부른다.
  - 객체 내부에 있고 우리는 확인 하기 어렵다.
- 스레드가 synchronized 키워드가 있는 메서드에 진입하려면 반드시 해당 인스턴스의 락이 있어야 한다.
  - 여기서는 BankAccount(x001) 인스턴스의 synchronized withdraw() 메서드를 호출하므로 이 인스턴스의 락이 필요하다.
- 스레드 t1, t2 는 withdraw() 를 실행하기 직전이다.

### 실습코드
```java
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

        // 잔고가 출금액 보다 적으면, 진행하면 안됨
        log("[검증 시작] 출급액: " + amount + ", 잔액: " + balance);
        if (balance < amount) {
            log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
            return false;
        }

        // 잔고가 출금액 보다 많으면, 진행
        log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000); // 출금에 걸리는 시간으로 가정
        balance = balance - amount;
        log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
```

- 쓰레드 1 번 입장에서 synchronized 키워드가 있으면 lock 을 획득해서 진입했다.
- 쓰레드 2 번 입장에서 x001 의 인스턴스에 lock 이 없네 ? -> Blocked 상태로 대기한다. (임시대기 상태) lock 을 획들할때 까지 무한정 기다리는것이 핵심
- CPU 실행 스케줄링에 들어가지도 않고 마치 sleep 상태 처럼 대기하는것이다.

> volatile 또는 Synchronized 나 Lock 같은 동기화 블록을 쓰면, 자동으로 메모리 가시성 문제가 해결된다.


### 장/단점
- 단점: 쓰레드가 줄을 서서 한번에 하나씩 실행해서 성능이 떨어진다.
  - 10 차선 도로 가 Synchronized 만나면 1차선으로 변경되는것이다. (병목 현상)
- 장점: 동시성 문제를 해결 ( 경쟁상태 / 레이스컨디션 )
  - 대응 : 1차선 구간을 짧게 만들면 된다.


