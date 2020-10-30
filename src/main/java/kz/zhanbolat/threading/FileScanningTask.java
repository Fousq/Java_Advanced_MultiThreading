package kz.zhanbolat.threading;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FileScanningTask extends RecursiveTask<ScanResult> {
    private String path;

    public FileScanningTask(String path) {
        this.path = path;
    }

    @Override
    protected ScanResult compute() {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Not existing path " + path);
        }
        int filesCount = 0;
        int foldersCount = 0;
        BigInteger size = BigInteger.ZERO;
        List<File> folders = new ArrayList<>();
        for (File listFile : file.listFiles()) {
            if (listFile.isFile()) {
                filesCount++;
                size = size.add(BigInteger.valueOf(listFile.length()));
            } else {
                foldersCount++;
                folders.add(listFile);
            }
        }
        if (folders.size() != 0) {
            Collection<FileScanningTask> tasks = ForkJoinTask.invokeAll(createSubtasks(folders));
            for (FileScanningTask task : tasks) {
                ScanResult scanResult = task.join();
                filesCount += scanResult.getFileCount();
                foldersCount += scanResult.getFolderCount();
                size = size.add(scanResult.getSize());
            }
        }
        return new ScanResult(filesCount, foldersCount, size);
    }

    private Collection<FileScanningTask> createSubtasks(List<File> folders) {
        List<FileScanningTask> tasks = new ArrayList<>();
        folders.forEach(folder -> tasks.add(new FileScanningTask(folder.getPath())));
        return tasks;
    }
}
