package com.barakugav.emagnetar;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultAutoConsumer extends DefaultConsumer implements AutoConsumer {

    private java.util.function.Consumer<Event> action;

    private final AtomicBoolean isRunning;
    private long cyclePeriod;
    private final Timer timer;
    private final TimerTask autoConsumeTask;

    private static final long DEFAULT_CYCLE_PERIOD = 1000;

    DefaultAutoConsumer(String topic) {
	super(topic);
	action = NOP.INSTANCE;

	isRunning = new AtomicBoolean(false);
	cyclePeriod = DEFAULT_CYCLE_PERIOD;
	timer = new Timer(true);
	autoConsumeTask = new AutoConsumeTask();
    }

    @Override
    public void setAction(java.util.function.Consumer<Event> action) {
	this.action = action != null ? action : NOP.INSTANCE;
    }

    @Override
    public void start() {
	if (isRunning.compareAndSet(false, true))
	    timer.schedule(autoConsumeTask, 0, cyclePeriod);
    }

    @Override
    public void stop() {
	if (!isRunning.compareAndSet(true, false))
	    timer.cancel();
    }

    @Override
    public void setCyclePeriod(long period) {
	if (period <= 0)
	    throw new IllegalArgumentException();
	cyclePeriod = period;
	if (isRunning.get()) {
	    stop();
	    start();
	}
    }

    @Override
    public String toString() {
	return "DefaultAutoConsumer[" + getTopic() + "]";
    }

    private class AutoConsumeTask extends TimerTask {

	@Override
	public void run() {
	    java.util.function.Consumer<Event> action = DefaultAutoConsumer.this.action;
	    for (Event event; (event = nextEvent()) != null;)
		action.accept(event);

	}

    }

    private static class NOP implements java.util.function.Consumer<Event> {

	static final NOP INSTANCE = new NOP();

	private NOP() {
	}

	@Override
	public void accept(Event t) {
	    // Do nothing
	}

    }

}
