package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);

        log("작업 중단 지시");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1: " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2: " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                // 위에서 이어온 인터럽트 때문에 예외가 터진다.
                Thread.sleep(1000);
                log("자원 정리");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 인터럽트 발생");
                log("work 스레드 인터럽트 상태3: " + Thread.currentThread().isInterrupted());
            }

            log("자원 종료");
        }
    }
}
