package me.anfanik.sharkly.utility.schedule;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public class BukkitScheduler implements Scheduler {

    private final Plugin plugin;
    private final org.bukkit.scheduler.BukkitScheduler bukkitScheduler;

    public BukkitScheduler(Plugin plugin) {
        this.plugin = plugin;
        this.bukkitScheduler = Bukkit.getScheduler();
    }

    @Override
    public void runTask(SchedulerTask task) {
        AtomicReference<BukkitTask> bukkitTask = new AtomicReference<>();
        Runnable runnable = () -> {
            if (task.isCancelled()) {
                bukkitTask.get().cancel();
            }
        };

        if (!task.isAsync()) {
            bukkitTask.set(bukkitScheduler.runTaskTimer(plugin,
                runnable,
                task.getDelay(),
                task.getPeriod()));
        } else {
            bukkitTask.set(bukkitScheduler.runTaskTimerAsynchronously(plugin,
                runnable,
                task.getDelay(),
                task.getPeriod()));
        }
    }

}
