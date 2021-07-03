package me.anfanik.sharkly.utility.schedule;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class SchedulerTask implements Runnable {

    private final Consumer<SchedulerTask> handler;
    private final boolean async;
    private final long delay;
    private final long period;

    private boolean started;
    private boolean cancelled;

    @Override
    public void run() {
        started = true;
        handler.accept(this);
    }

    public void cancel() {
        cancelled = true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Consumer<SchedulerTask> handler;
        private boolean async = false;
        private long delay = 0;
        private long period = -1;
        private int times = 1;

        public Builder handler(Runnable handler) {
            this.handler = task -> handler.run();
            return this;
        }

        public Builder handler(Consumer<SchedulerTask> handler) {
            this.handler = handler;
            return this;
        }

        public Builder sync() {
            async = false;
            return this;
        }

        public Builder async() {
            async = true;
            return this;
        }

        public Builder delay(long delay) {
            this.delay = delay;
            return this;
        }

        public Builder period(long period) {
            this.period = period;
            return this;
        }

        public Builder times(int times) {
            this.times = times;
            return this;
        }

        public SchedulerTask build() {
            Preconditions.checkNotNull(handler, "handler is null");
            return new SchedulerTask(times == 1 ? handler : new Consumer<SchedulerTask>() {
                private int run = 0;
                @Override
                public void accept(SchedulerTask schedulerTask) {
                    if (run < times) {
                        handler.accept(schedulerTask);
                        run++;
                    } else {
                        schedulerTask.cancel();
                    }
                }
            }, async, delay, period);
        }

    }

}
