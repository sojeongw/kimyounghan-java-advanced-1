package thread.control.yield;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
                // 2. sleep: timed waiting으로 넘어가서 다른 스레드에 실행을 양보했다가 runnable로 돌아온다.
                // 다른 스레드가 다 쉬고 있어도 나까지 쉬어야 한다.
//                sleep(1);
                // 3. yield: runnable 상태를 그대로 유지하고 잠깐 쉰다.
                // 양보할 스레드가 없다면 본인 스레드가 계속 실행될 수 있다.
                Thread.yield();
            }
        }
    }
}
