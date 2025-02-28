package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득 참. 생산자 대기");
            try {
                // runnable -> waiting으로 전환하면서 락을 반납한다.
                wait();
                log("[put] 생산자 꺠어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.offer(data);
        log("[put] 생산자 데이터 저장. notify() 호출");
        // waiting -> blocked
        notify();
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음. 소비자 대기");
            try {
                wait();
                log("[take] 소비자 꺠어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 획득. notify() 호출");
        // waiting -> blocked
        notify();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
