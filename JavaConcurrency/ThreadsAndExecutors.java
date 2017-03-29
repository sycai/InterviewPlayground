package JavaConcurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by sycai on 3/27/2017.
 */
public class ThreadsAndExecutors {
    public static void main(String[] args) {
        //newThreadTest();
        //threadSleepTest();
        //executorServiceTest();
        //callablesAndFutureTest();
        //timeoutsTest();
        //invokeAllTest();
        //invokeAnyTest();
        scheduledExecutorTest();
    }

    private static void newThreadTest() {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run(); // This is the main thread

        Thread thread = new Thread(task); // Create a new thread
        thread.start();

        System.out.println("Done!");
    }

    private static void threadSleepTest() {
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1); // Sleep for one second
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void executorServiceTest() {
        // Executors are capable of running asynchronous tasks and typically manage a pool of threads
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);

            // Executors have to be stopped explicitly
            // The following code is an example of a shutdown routine
            try {
                System.out.println("attempt to shutdown executor");
                executor.shutdown(); // Wait for currently running tasks to finish ...
                executor.awaitTermination(5, TimeUnit.SECONDS); // ... for five seconds
            } catch (InterruptedException e) {
                System.err.println("tasks interrupted");
            } finally {
                if (!executor.isTerminated()) {
                    System.err.println("cancel non-finished tasks");
                }
                executor.shutdownNow(); // Interrupts all running tasks and shut the executor down immediately.
                System.out.println("shutdown finished");
            }
        });
    }

    private static void callablesAndFutureTest() {
        // Callables are functional interfaces that return a value
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        try {
            Integer result = future.get(); // This blocks the current thread and wait for the callable to complete
            System.out.println("future done? " + future.isDone());
            System.out.println("result: " + result);
        } catch (ExecutionException e) {
            System.err.println("execution exception!");
        } catch (InterruptedException e) {
            System.err.println("task interrrupted!");
        }

        executor.shutdownNow(); // Explicitly shut down the executor
    }

    private static void timeoutsTest() {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(() -> {
           try {
               TimeUnit.SECONDS.sleep(2);
               return 123;
           } catch (InterruptedException e) {
               throw new IllegalStateException("task interrupted", e);
           }
        });
        try {
            future.get(1, TimeUnit.SECONDS); // Set max block time to 1 second
        } catch (Exception e) {
            System.err.println(e);
        }

        executor.shutdownNow();
    }

    private static void invokeAllTest() {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3"
        );
        try {
            // invokeAll() accepts a collection of callables and returns a list of futures
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            System.err.println("tasks interrrupted");
        }
    }

    // A helper method
    private static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    private static void invokeAnyTest() {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3)
        );
        try {
            // invokeAny() blocks the current thread until the first callable terminates
            // and returns the result of that callable
            String result = executor.invokeAny(callables);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void scheduledExecutorTest() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        };

        // Delay execution for 3 seconds
        // ScheduledFuture<?> future = executor.schedule(task,3,TimeUnit.SECONDS);
        // scheduleAtFixedRate specify the delay between the start of an execution and the start of the next.
        // ScheduledFuture<?> future = executor.scheduleAtFixedRate(task,0,3,TimeUnit.SECONDS);
        // scheduleWithFixedDelay specify the delay between the end of an execution and the start of the next.
        executor.scheduleWithFixedDelay(task,0,1,TimeUnit.SECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        // long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        // System.out.printf("Remaining Delay: %sms\n", remainingDelay);
    }
}
