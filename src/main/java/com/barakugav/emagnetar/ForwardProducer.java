package com.barakugav.emagnetar;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class ForwardProducer extends DefaultProducer {

    private final Consumer source;

    private static final Syncer syncer = new Syncer();

    public ForwardProducer(String sourceTopic, String destinationTopic) {
	this(Consumer.newConsumer(sourceTopic), destinationTopic);
    }

    public ForwardProducer(Consumer source, String destinationTopic) {
	super(destinationTopic);
	this.source = Objects.requireNonNull(source);
	syncer.register(this);
    }

    public Consumer getSource() {
	return source;
    }

    protected void handleEventFromSource(Event event) {
	postEvent(event);
    }

    private static class Syncer extends TimerTask {

	private final List<ForwardProducer> forwardProducers;

	private static final long syncerCycle = 1000;

	private Syncer() {
	    forwardProducers = new CopyOnWriteArrayList<>();

	    Timer syncTimer = new Timer(ForwardProducer.class.getName() + ".syncTimer", true);
	    syncTimer.schedule(this, System.currentTimeMillis(), syncerCycle);
	}

	@Override
	public void run() {
	    for (ForwardProducer forwardProducer : forwardProducers) {
		Event event;
		while ((event = forwardProducer.source.nextEvent()) != null)
		    forwardProducer.handleEventFromSource(event);
	    }
	}

	void register(ForwardProducer forwardProducer) {
	    forwardProducers.add(forwardProducer);
	}

    }

}
