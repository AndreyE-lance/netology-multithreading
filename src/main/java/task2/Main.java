package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
        List<Callable<String>> callables = new ArrayList<>();
        Callable<String> myCallable = new MyThread();
        for (int i = 0; i < availableProcessors; i++) {
            callables.add(myCallable);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. InvokeAny \n2. InvokeAll \nили любую кнопку и Enter для выхода.");
        String input = bufferedReader.readLine();
        switch (input) {
            case "1":
                invokeAny(executor, callables);
                break;
            case "2":
                invokeAll(executor, callables);
                break;
            default:
                break;
        }
    }

    private static void invokeAny(ExecutorService executor, List<Callable<String>> callables) {
        String result = null;
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println(result);
    }

    private static void invokeAll(ExecutorService executor, List<Callable<String>> callables) {
        List<Future<String>> futures = null;
        try {
            futures = executor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
