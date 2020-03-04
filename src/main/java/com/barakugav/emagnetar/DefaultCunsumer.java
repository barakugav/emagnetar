package com.barakugav.emagnetar;

public class DefaultCunsumer extends AbstractConsumer {

    private int curser;

    DefaultCunsumer(String topic) {
	super(topic);
	curser = 0;
    }

    @Override
    public Event nextEvent() {
	Topic topic = topic();
	EventRecord record;
	synchronized (topic.eventsRecords) {
	    if (topic.eventsRecords.size() <= curser)
		return null;
	    record = topic.eventsRecords.get(curser++);
	}
	return Event.valueOf(record, getDeserializer());
    }

}
