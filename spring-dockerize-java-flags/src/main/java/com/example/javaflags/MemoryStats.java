package com.example.javaflags;

public class MemoryStats {
    private long totalMemory;
    private long maxMemory;
    private long freeMemory;

    public MemoryStats() {
    }

    public MemoryStats(long totalMemory, long maxMemory, long freeMemory) {
        this.totalMemory = totalMemory;
        this.maxMemory = maxMemory;
        this.freeMemory = freeMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }
}