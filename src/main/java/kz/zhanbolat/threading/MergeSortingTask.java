package kz.zhanbolat.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MergeSortingTask extends RecursiveTask<List<Integer>> {
    private static final int THRESHOLD = 10;
    private List<Integer> list;

    public MergeSortingTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected List<Integer> compute() {
        List<Integer> sortedList;
        List<Integer> unsortedList = new ArrayList<>();
        if (list.size() > THRESHOLD) {
            Collection<MergeSortingTask> tasks = ForkJoinTask.invokeAll(createSubtasks());
            for (MergeSortingTask task : tasks) {
                unsortedList.addAll(task.join());
            }
        } else {
            unsortedList.addAll(list);
        }
        sortedList = sort(unsortedList);
        return sortedList;
    }

    private List<Integer> sort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            Integer number = list.get(i);
            int index = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (number > list.get(j)) {
                    number = list.get(j);
                    index = j;
                }
            }
            list.set(index, list.get(i));
            list.set(i, number);
        }
        return list;
    }

    private Collection<MergeSortingTask> createSubtasks() {
        List<MergeSortingTask> tasks = new ArrayList<>();
        tasks.add(new MergeSortingTask(list.subList(0, list.size() / 2)));
        tasks.add(new MergeSortingTask(list.subList(list.size() / 2, list.size())));
        return tasks;
    }
}
