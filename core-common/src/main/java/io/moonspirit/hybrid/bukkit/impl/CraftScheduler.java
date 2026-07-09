package io.moonspirit.hybrid.bukkit.impl;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class CraftScheduler implements BukkitScheduler {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private int taskIdCounter = 0;

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay) {
        scheduledExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
        return ++taskIdCounter;
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay) {
        return scheduleSyncDelayedTask(plugin, (Runnable) task, delay);
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task) {
        return scheduleSyncDelayedTask(plugin, task, 0L);
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task) {
        return scheduleSyncDelayedTask(plugin, (Runnable) task, 0L);
    }

    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period) {
        scheduledExecutor.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        return ++taskIdCounter;
    }

    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period) {
        return scheduleSyncRepeatingTask(plugin, (Runnable) task, delay, period);
    }

    @Override
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay) {
        executor.submit(task);
        return ++taskIdCounter;
    }

    @Override
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task) {
        return scheduleAsyncDelayedTask(plugin, task, 0L);
    }

    @Override
    public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period) {
        executor.submit(task);
        return ++taskIdCounter;
    }

    @Override
    public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task) {
        FutureTask<T> future = new FutureTask<>(task);
        future.run();
        return future;
    }

    @Override
    public void cancelTask(int taskId) {
    }

    @Override
    public void cancelTasks(Plugin plugin) {
    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        return false;
    }

    @Override
    public boolean isQueued(int taskId) {
        return false;
    }

    @Override
    public List<BukkitWorker> getActiveWorkers() {
        return Collections.emptyList();
    }

    @Override
    public List<BukkitTask> getPendingTasks() {
        return Collections.emptyList();
    }

    @Override
    public BukkitTask runTask(Plugin plugin, Runnable task) throws IllegalArgumentException {
        executor.submit(task);
        return null;
    }

    @Override
    public void runTask(Plugin plugin, Consumer<BukkitTask> task) throws IllegalArgumentException {
        executor.submit(() -> task.accept(null));
    }

    @Override
    public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        return runTask(plugin, (Runnable) task);
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task) throws IllegalArgumentException {
        executor.submit(task);
        return null;
    }

    @Override
    public void runTaskAsynchronously(Plugin plugin, Consumer<BukkitTask> task) throws IllegalArgumentException {
        executor.submit(() -> task.accept(null));
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        return runTaskAsynchronously(plugin, (Runnable) task);
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException {
        scheduledExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
        return null;
    }

    @Override
    public void runTaskLater(Plugin plugin, Consumer<BukkitTask> task, long delay) throws IllegalArgumentException {
        scheduledExecutor.schedule(() -> task.accept(null), delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        return runTaskLater(plugin, (Runnable) task, delay);
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException {
        executor.submit(task);
        return null;
    }

    @Override
    public void runTaskLaterAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay) throws IllegalArgumentException {
        executor.submit(() -> task.accept(null));
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        return runTaskLaterAsynchronously(plugin, (Runnable) task, delay);
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException {
        scheduledExecutor.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        return null;
    }

    @Override
    public void runTaskTimer(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        scheduledExecutor.scheduleAtFixedRate(() -> task.accept(null), delay, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        return runTaskTimer(plugin, (Runnable) task, delay, period);
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException {
        executor.submit(task);
        return null;
    }

    @Override
    public void runTaskTimerAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        executor.submit(() -> task.accept(null));
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        return runTaskTimerAsynchronously(plugin, (Runnable) task, delay, period);
    }
}
