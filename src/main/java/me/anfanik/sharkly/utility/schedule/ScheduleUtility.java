package me.anfanik.sharkly.utility.schedule;

import lombok.val;
import me.anfanik.sharkly.Sharkly;
import me.anfanik.sharkly.utility.StackUtility;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScheduleUtility {

    private static final Plugin sharkly = JavaPlugin.getProvidingPlugin(Sharkly.class);
    private static final Map<Plugin, Scheduler> schedulers = new HashMap<>();

    private static Plugin getTaskPlugin() {
        return StackUtility.findFirstStackClassByPlugin()
            .orElse(sharkly);
    }

    private static Scheduler getScheduler(Plugin plugin) {
        return schedulers.computeIfAbsent(plugin, BukkitScheduler::new);
    }

    private static SchedulerTask runTask(SchedulerTask task) {
        getScheduler(getTaskPlugin()).runTask(task);
        return task;
    }

    public static SchedulerTask run(Consumer<SchedulerTask> action) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .build());
    }

    public static SchedulerTask run(Runnable action) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .build());
    }


    public static SchedulerTask runAsync(Consumer<SchedulerTask> action) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .build());
    }

    public static SchedulerTask runAsync(Runnable action) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .build());
    }


    public static SchedulerTask runDelayed(Consumer<SchedulerTask> action, long delay) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .delay(delay)
            .build());
    }

    public static SchedulerTask runDelayed(Runnable action, long delay) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .delay(delay)
            .build());
    }


    public static SchedulerTask runAsyncDelayed(Consumer<SchedulerTask> action, long delay) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .delay(delay)
            .build());
    }

    public static SchedulerTask runAsyncDelayed(Runnable action, long delay) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .delay(delay)
            .build());
    }


    public static SchedulerTask runRepeating(Consumer<SchedulerTask> action, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .period(period)
            .build());
    }

    public static SchedulerTask runRepeating(Runnable action, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .period(period)
            .build());
    }


    public static SchedulerTask runAsyncRepeating(Consumer<SchedulerTask> action, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .period(period)
            .async()
            .build());
    }

    public static SchedulerTask runAsyncRepeating(Runnable action, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .period(period)
            .async()
            .build());
    }


    public static SchedulerTask runDelayedRepeating(Consumer<SchedulerTask> action, long delay, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .delay(delay)
            .period(period)
            .build());
    }

    public static SchedulerTask runDelayedRepeating(Runnable action, long delay, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .delay(delay)
            .period(period)
            .build());
    }


    public static SchedulerTask runAsyncDelayedRepeating(Runnable action, long delay, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .delay(delay)
            .period(period)
            .build());
    }

    public static SchedulerTask runAsyncDelayedRepeating(Consumer<SchedulerTask> action, long delay, long period) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .delay(delay)
            .period(period)
            .build());
    }


    public static SchedulerTask runRepeatingForTimes(Consumer<SchedulerTask> action, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .period(period)
            .times(times)
            .build());
    }

    public static SchedulerTask runRepeatingForTimes(Runnable action, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .period(period)
            .times(times)
            .build());
    }


    public static SchedulerTask runAsyncRepeatingForTimes(Consumer<SchedulerTask> action, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .period(period)
            .times(times)
            .build());
    }

    public static SchedulerTask runAsyncRepeatingForTimes(Runnable action, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .period(period)
            .times(times)
            .build());
    }


    public static SchedulerTask runDelayedRepeatingForTimes(Consumer<SchedulerTask> action, long delay, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .delay(delay)
            .period(period)
            .times(times)
            .build());
    }

    public static SchedulerTask runDelayedRepeatingForTimes(Runnable action, long delay, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .delay(delay)
            .period(period)
            .times(times)
            .build());
    }


    public static SchedulerTask runAsyncDelayedRepeatingForTimes(Consumer<SchedulerTask> action, long delay, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .delay(delay)
            .period(period)
            .times(times)
            .build());
    }

    public static SchedulerTask runAsyncDelayedRepeatingForTimes(Runnable action, long delay, long period, int times) {
        return runTask(SchedulerTask.builder()
            .handler(action)
            .async()
            .delay(delay)
            .period(period)
            .times(times)
            .build());
    }

}
