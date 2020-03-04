package com.barakugav.emagnetar;

abstract class AbstractEventProducer implements EventProducer {

    private final Topic topic;
    private Serializer serializer;

    AbstractEventProducer(String topic) {
	this.topic = EventCenter.topic(topic);
	serializer = Serializer.getDefault();
    }

    @Override
    public String getTopic() {
	return topic.name;
    }

    Topic topic() {
	return topic;
    }

    @Override
    public Serializer getSerializer() {
	return serializer;
    }

    @Override
    public void setSerializer(Serializer serializer) {
	this.serializer = serializer != null ? serializer : Serializer.getDefault();
    }

}
