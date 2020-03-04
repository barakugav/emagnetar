package com.barakugav.emagnetar;

class DefaultConsumerGroup implements ConsumerGroup {

    private int curser;
    private final Topic topic;

    DefaultConsumerGroup(String topic) {
	this.topic = EMagnetar.topic(topic);
	curser = 0;
    }

    @Override
    public String getTopic() {
	return topic.name;
    }

    @Override
    public Consumer newConsumer() {
	return new DefaultGroupConsumer();
    }

    private class DefaultGroupConsumer extends AbstractConsumer {

	@Override
	public String getTopic() {
	    return DefaultConsumerGroup.this.getTopic();
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

}
