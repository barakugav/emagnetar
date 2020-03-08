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
	return topic.getName();
    }

    @Override
    public Consumer newConsumer() {
	return new DefaultGroupConsumer();
    }

    @Override
    public String toString() {
	return "DefaultConsumerGroup[" + getTopic() + "]";
    }

    private class DefaultGroupConsumer extends AbstractConsumer {

	@Override
	public String getTopic() {
	    return DefaultConsumerGroup.this.getTopic();
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

    }

}
