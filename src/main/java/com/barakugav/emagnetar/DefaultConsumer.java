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
	return topic.getName();
    }

    @Override
    public Event nextEvent() {
	EventRecord record;
	synchronized (this) {
	    record = topic.getEventRecord(curser);
	    if (record != null)
		curser++;
	}
	return record != null ? Event.valueOf(record, getDeserializer()) : null;
    }

    @Override
    public String toString() {
	return "DefaultConsumer[" + getTopic() + "]";
    }

}
