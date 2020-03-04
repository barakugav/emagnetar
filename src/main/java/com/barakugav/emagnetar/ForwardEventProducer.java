package com.barakugav.emagnetar;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class ForwardEventProducer extends DefaultEventProducer {

    private final EventCunsumer source;

    private static final Syncer syncer = new Syncer();

    public ForwardEventProducer(String sourceTopic, String destinationTopic) {
	this(EventCunsumer.newEventCunsumer(sourceTopic), destinationTopic);
    }

    public ForwardEventProducer(EventCunsumer source, String destinationTopic) {
	super(destinationTopic);
	this.source = Objects.requireNonNull(source);
	syncer.register(this);
    }

    public EventCunsumer getEventSource() {
	return source;
    }

    public void handleEventFromSource(Event event) {
	postEvent(event);
    }

    private static class Syncer extends TimerTask {

	private final List<ForwardEventProducer> forwardProducers;

	private static final long syncerCycle = 1000;

	private Syncer() {
	    forwardProducers = new CopyOnWriteArrayList<>();

	    Timer syncTimer = new Timer(ForwardEventProducer.class.getName() + ".syncTimer", true);
	    syncTimer.schedule(this, System.currentTimeMillis(), syncerCycle);
	}

	@Override
	public void run() {
	    for (ForwardEventProducer forwardProducer : forwardProducers) {
		Event event;
		while ((event = forwardProducer.source.nextEvent()) != null)
		    forwardProducer.handleEventFromSource(event);
	    }
	}

	void register(ForwardEventProducer forwardProducer) {
	    forwardProducers.add(forwardProducer);
	}

    }

}
