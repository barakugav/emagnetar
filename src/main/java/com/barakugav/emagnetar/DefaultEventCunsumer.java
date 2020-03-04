package com.barakugav.emagnetar;

public class DefaultEventCunsumer extends AbstractEventConsumer {

    private int curser;

    DefaultEventCunsumer(String topic) {
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
