package at.smartsced.rsickinger.demo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Class that sums the elements of sublists using the fork/join framework
 */
public class SumTask extends RecursiveTask<Integer> {
    private static final int SEQUENTIAL_THRESHOLD = 5;

    // The list with the numbers
    private List<Integer> data;

    // Since compute() doesn't take parameters, you have to
    // pass in the task's constructor the data to work
    public SumTask(List<Integer> data) {
        this.data = data;
    }

    //Return type matches the generic
    @Override
    protected Integer compute() {
        if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
            Integer sum = computeSumDirectly();
            //System.out.format("Sum of %s: %d\n", data.toString(), sum);
            return sum;
        } else { // recursive case
            // Calculate new range
            int mid = data.size() / 2;
            SumTask firstSubtask =
                    new SumTask(data.subList(0, mid));
            SumTask secondSubtask =
                    new SumTask(data.subList(mid, data.size()));

            invokeAll(firstSubtask, secondSubtask);

            // Return the sum of all subtasks
            return firstSubtask.join() + secondSubtask.join();
        }
    }

    /** Method that calculates the sum */
    private int computeSumDirectly() {
        int sum = 0;
        for (Integer l: data) {
            sum += l;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> data = IntStream.range(1, 501).boxed().collect(toList());

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Pool parallelism: " + pool.getParallelism());
        SumTask task = new SumTask(data);
        System.out.println("Sum: " + pool.invoke(task));
    }
}