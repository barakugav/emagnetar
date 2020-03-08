package com.barakugav.emagnetar;

import java.util.Objects;

class DefaultProducer extends AbstractProducer {

    private final Topic topic;

    DefaultProducer(String topic) {
	this.topic = EMagnetar.topic(topic);
    }

    @Override
    public String getTopic() {
	return topic.getName();
    }

    @Override
    public boolean postEvent(Event event) {
	Objects.requireNonNull(event);
	EventRecord record = EventRecord.valueOf(event, getSerializer());
	return topic.postEventRecord(record);
    }

    @Override
    public String toString() {
	return "DefaultProducer[" + getTopic() + "]";
    }

}
