package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

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
            // 인터럽트인지 확인하고 인터럽트면 false로 바꿔준다.
            while (!Thread.interrupted()) {
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2: " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
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
