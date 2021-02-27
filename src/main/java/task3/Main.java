package task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Подождите. Формируется массив...");
        int[] array = generateArray(100_000_000);
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println("старт");

        long startTime = System.currentTimeMillis();
        System.out.println("Сумма:" + simpleSumArray(array));
        long endTime = System.currentTimeMillis();
        System.out.println("Последовательный подсчет элементов: " + (endTime - startTime) + " мс");

        startTime = System.currentTimeMillis();
        System.out.println("Сумма:" + streamSumArray(list));
        endTime = System.currentTimeMillis();
        System.out.println("Подсчет через stream: " + (endTime - startTime) + " мс");

        startTime = System.currentTimeMillis();
        System.out.println("Сумма:" + parallelStreamSumArray(list));
        endTime = System.currentTimeMillis();
        System.out.println("Подсчет через parallelStream: " + (endTime - startTime) + " мс");

        ForkJoinPool fjPool = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        System.out.println("Сумма:" + fjPool.invoke(new MyRecursiveTask(0, array.length, array)));
        endTime = System.currentTimeMillis();
        System.out.println("Подсчет через ForkJoinPool: " + (endTime - startTime) + " мс");
    }

    private static int[] generateArray(int elementsAmount) {
        int[] array = new int[elementsAmount];
        for (int i = 0; i < elementsAmount; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    private static long simpleSumArray(int[] array) {
        long result = 0;
        for (int i : array) {
            result += i;
        }
        return result;
    }

    private static long streamSumArray(List<Integer> list) {
        return list.stream().mapToLong(l -> l).sum();
    }

    private static long parallelStreamSumArray(List<Integer> list) {
        return list.parallelStream().mapToLong(l -> l).sum();
    }
}
