package kz.zhanbolat.threading;

import java.math.BigInteger;
import java.util.Objects;

public class ScanResult {
    private int fileCount;
    private int folderCount;
    private BigInteger size;

    public ScanResult(int fileCount, int folderCount, BigInteger size) {
        this.fileCount = fileCount;
        this.folderCount = folderCount;
        this.size = size;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public int getFolderCount() {
        return folderCount;
    }

    public void setFolderCount(int folderCount) {
        this.folderCount = folderCount;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScanResult that = (ScanResult) o;
        return fileCount == that.fileCount &&
                folderCount == that.folderCount &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileCount, folderCount, size);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "fileCount=" + fileCount +
                ", folderCount=" + folderCount +
                ", size=" + size +
                '}';
    }
}
