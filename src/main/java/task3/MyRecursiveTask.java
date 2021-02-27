package task3;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {
    private final int[] array;
    private final int start;
    private final int end;

    public MyRecursiveTask(int start, int end, int[] array) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int mid = array.length / 2;
        final int diff = end - start;
        switch (diff) {
            case 0:
                return 0L;
            case 1:
                return (long) (array[start]);
            case 2:
                return (long) (array[start] + array[start + 1]);
            default:
                return forkTasksAndGetResult();
        }
    }

    private Long forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        MyRecursiveTask task1 = new MyRecursiveTask(start, middle, array);
        MyRecursiveTask task2 = new MyRecursiveTask(middle, end, array);
        invokeAll(task1, task2);
        return task1.join() + task2.join();
    }
}
