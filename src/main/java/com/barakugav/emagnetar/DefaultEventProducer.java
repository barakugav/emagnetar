package com.barakugav.emagnetar;

import java.util.Objects;

public class DefaultEventProducer extends AbstractEventProducer {

    DefaultEventProducer(String topic) {
	super(topic);
    }

    @Override
    public void postEvent(Event event) {
	Objects.requireNonNull(event);
	EventRecord record = EventRecord.valueOf(event, getSerializer());
	Topic topic = topic();
	synchronized (topic.eventsRecords) {
	    topic.eventsRecords.add(record);
	}
    }

}
