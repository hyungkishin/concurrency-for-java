### synchronized 단점
- 공정성: 락이 돌아왔을때 Blocked 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할지 알 수 없다. 최악의 경우 특정 스레드가 너무 오랜기간 락을 획득하지 못할 수 있다.

> Lock 인터페이스의 대표적인 구현체로 ReentrantLock 이 있는데, 이 클래스는 스레드가 공정하게 락을 얻을 수 있는 모드를 제공한다.


### 공정 모드 특징
- 공정성 보장: 대기 큐에서 먼저 대기한 스레드가 락을 먼저 획득한다.
- 기아 현상 방지: 모든 스레드가 언젠가 락을 획득할 수있게 보장된다.
- 성능 저하: 락을 획득하는 속도가 느려질 수 있다.

### 비공정, 공정 모드 정리
- 비공정 모드는 성능을 중시하고, 스레드가 락을 빨리 획득할 수 있지만, 특정 스레드가 계속해서 락을 획득하지 못할 수 있다.
- 공정 모드는 스레드가 락을 획득하는 순서를 보장하여 공정성을 중시하지만, 성능이 저하될 수 있다.

### 정리
- Lock 인터페이스와 ReentrantLock 구현체를 사용하면 synchronized 단점인 무한 대기와 공정성 문제를 모두 해결할 수 있다.

### ReentrantLock - 대기중단
- ReentrantLock 을 사용하면 락을 무한 대기하지 않고, 중간에 빠져나오는 것이 가능하다. 심지어 락을 얻을 수 없다면 기다리지 않고 즉시 빠져나오는 것도 가능하다.

### boolean tryLock()
- 락 획득을 시도하고, 즉시 성공 여부를 반환한다. 만약 다른 스레드가 이미 락을 획득했다면 false 를 반환하고, 그렇지 않으면 락을 획득하고 true 를 반환한다.
- 예) 맛집에 대기 줄이 없으면 바로 들어가고, 대기 줄이 있으면 즉시 포기한다.

### boolean tryLock(long time, TimeUnit unit)
- 주어진 시간동안 락 획득을 시도한다. 주어진 시간 안에 락을 획득하면 true 를 반환한다. 주어진 시간이 지나도 락을 획득하지 못한 경우 false 를 반환한다.
  - 이 메서드는 대기 중 인터럽트가 발생하면 InterruptedException 이 발생하며 락 획득을 포기한다.
- 예) 맛집에 줄을 서지만 특정 시간 만큼만 기다린다. 특정 시간이 지나도 계속 줄을 서야 한다면 포기한다. 친구가 다른 맛집을 찾았다고 중간에 연락해도 포기한다.

## 정리
### LockSupoort
- LockSupport 는 스레드를 WAITING 상태로 변경한다.
  - WAITING 상태는 누가 깨워주기 전까지는 계속 대기한다. 그리고 CPU 실행 스케줄링에 들어가지 않는다.
- LockSupport 의 대표적인 기능은 다음과 같다
  - park(): 스레드를 WAITING 상태로 변경한다.
    - 스레드를 대기 상태로 둔다. 참고로 park 는 "주차하다", "두다" 라는 뜻이다.
  - parkNanos(nanos): 스레드를 나노초 동안만 TIMED_WAITING 상태로 변경한다
    - 지정한 나노초가 지나면 TIMED_WAITING 상태에서 빠져나오고 RUNNABLE 상태로 변경된다.
  - unpark(thread): WAITING 상태의 대상 스레드를 RUNNABLE 상태로 변경한다.
- WAITING 상태라 인터럽트를 걸 수 있다.

### BLOCKED vs WAITING 
- WAITING 상태에 특정 시간까지만 대기하는 기능이 포함된 것이 TIMED_WAITING

### 인터럽트
- BLOCKED 상태는 인터럽트가 걸려도 대기 상태를 빠져나오지 못한다. 여전히 BLOCKED 상태이다.
- WAITING, TIMED_WAITING 상태는 인터럽트가 걸리면 대기 상태를 빠져나온다. 그래서 RUNNABLE 상태로 변한다.

### 용도
- BLCOKED 상태는 자바의 synchronized 에서 락을 획득하기 위해 대기할 때 사용된다.
- WAITING, TIMED_WAITING 상태는 스레드가 특정 조건이나 시간 동안 대기할 때 발생하는 상태이다.
- WAITING 상태는 다양한 상황에서 사용된다. 예를 들어, Thread.join(), LockSupport.park(), Object.wait() 와 같은 메서드 호출 시 WAITING 상태가 된다.
- TIMED_WAITING 상태는 Thread.sleep(ms), Object.wait(long timeout), Thread.join(long millis), LockSupport.parkNanos(ns) 등과 같은 시간 제한이 있는 대기 메서드를 호출할 때 발생한다.

### 대기
- Thread.join(), Thread.join(long millis)
- Thread.park(), Thread.parkNanos(long millis)
- Object.wait(), Object.wait(long timeout)

### LockSupport 를 사용하면
- synchronized 의 무한대기 상태를 해결 할 수 이싿.
  - synchronized 는 특정 시간까지만 대기하는 타임아웃이 불가능 했지만, parkNanos() 를 사용하면 특정시간 까지만 대기할 수 있다.
  - synchronized 는 중간에 인터럽트를 걸 수 없으나, park(), parkNanos() 는 인터럽트를 걸 수 있다.

> 그러나 너무 귀찮다. 그래서 Java 에서 제공하는 Lock 인터페이스와 ReentrantLock 이라는 구현체로 이런 기능들을 이미 구현해둔것을 사용하면 된다.
