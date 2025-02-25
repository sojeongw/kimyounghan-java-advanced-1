package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("start");

        SumTask task1 = new SumTask(1, 50);
        Thread thread1 = new Thread(task1);

        SumTask task2 = new SumTask(51, 100);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        // 해당 자식 스레드가 종료할 때까지 대기
        log("join() - main 스레드가 thread1, 2 종료까지 대기");
        thread1.join();
        thread2.join();
        log("join() - main 스레드 대기 완료");

        log("task1.thread = " + task1.result);
        log("task2.thread = " + task2.result);

        int sumAll = task1.result + task2.result;
        log("task1 + task2 = " + sumAll);

        log("end");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            result = sum;
            log("작업 완료. result = " + result);
        }
    }
}
