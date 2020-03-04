package com.barakugav.emagnetar;

class DefaultConsumer extends AbstractConsumer {

    private int curser;
    private final Topic topic;

    DefaultConsumer(String topic) {
	this.topic = EMagnetar.topic(topic);
	curser = 0;
    }

    @Override
    public String getTopic() {
	return topic.name;
    }

    @Override
    public Event nextEvent() {
	EventRecord record;
	synchronized (topic.eventsRecords) {
	    if (topic.eventsRecords.size() <= curser)
		return null;
	    record = topic.eventsRecords.get(curser++);
	}
	return Event.valueOf(record, getDeserializer());
    }

}
