### Blocked vs Waiting  
- WAITING 상태에 에 특정 시간 까지만 대기하는 기능이 포함된 것이 TIMED_WAITING.

### 인터럽트 
- Blocked 상태는 인터럽트가 걸려도 대기 상태를 빠져나오지 못한다. 여전히 Blocked 상태이다.
- Waiting, Timed_waiting 상태는 인터럽트가 걸리면 대기 상태를 빠져나온다. 그래서 RUNNABLE 상태로 변한다.

### LockSupport 정리
- LockSupport 를 사용하면 스레드를 WAITING, TIMED_WAITING 상태로 변경할 수 있고, 또 인터럽트를 받아서 스레드를 깨울 수도 있다.
  - 위 기능을 활용해서 synchronized 의 단점인 무한 대기 문제를 해결할 수 있을것 같다.

