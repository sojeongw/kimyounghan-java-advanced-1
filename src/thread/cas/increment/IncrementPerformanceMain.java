package thread.cas.increment;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInteger());   // cpu 캐시를 사용하므로 제일 빠르다.
        test(new VolatileInteger());    // 더 느리고 멀티 스레드에 안전하지 않다.
        test(new SyncInteger());    // MyAtomicInteger보다 느리다.
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();

        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms = " + (endMs - startMs));
    }
}
