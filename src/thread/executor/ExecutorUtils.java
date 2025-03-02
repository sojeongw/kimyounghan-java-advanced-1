package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor threadPoolExecutor) {
            // 스레드 풀에서 관리하는 수
            int pool = threadPoolExecutor.getPoolSize();
            // 작업을 수행하는 스레드 수
            int active = threadPoolExecutor.getActiveCount();
            // 큐에 대기중인 작업 수
            int queuedTasks = threadPoolExecutor.getQueue().size();
            // 완료된 작업 수
            long completedTask = threadPoolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }

    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor threadPoolExecutor) {
            // 스레드 풀에서 관리하는 수
            int pool = threadPoolExecutor.getPoolSize();
            // 작업을 수행하는 스레드 수
            int active = threadPoolExecutor.getActiveCount();
            // 큐에 대기중인 작업 수
            int queuedTasks = threadPoolExecutor.getQueue().size();
            // 완료된 작업 수
            long completedTask = threadPoolExecutor.getCompletedTaskCount();
            log(taskName + " -> [pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }
}
