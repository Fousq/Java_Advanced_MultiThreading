package kz.zhanbolat.threading;

import me.tongfei.progressbar.ProgressBar;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FileScanningTask extends RecursiveTask<ScanResult> {
    private File directory;
    private ProgressBar progressBar;

    public FileScanningTask(File directory, ProgressBar progressBar) {
        this.directory = directory;
        this.progressBar = progressBar;
    }

    @Override
    protected ScanResult compute() {
        if (!directory.exists()) {
            throw new IllegalArgumentException("Not existing path " + directory.getPath());
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The directory should be specified");
        }
        int filesCount = 0;
        int foldersCount = 0;
        BigInteger size = BigInteger.ZERO;
        List<File> folders = new ArrayList<>();
        for (File listFile : directory.listFiles()) {
            if (listFile.isFile()) {
                filesCount++;
                size = size.add(BigInteger.valueOf(listFile.length()));
            } else {
                foldersCount++;
                folders.add(listFile);
            }
        }
        progressBar.stepBy(size.longValue());
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
        folders.forEach(folder -> tasks.add(new FileScanningTask(folder, progressBar)));
        return tasks;
    }
}
