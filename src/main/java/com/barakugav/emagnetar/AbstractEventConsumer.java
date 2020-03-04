package com.barakugav.emagnetar;

abstract class AbstractEventConsumer implements EventCunsumer {

    private final Topic topic;
    private Deserializer deserializer;

    AbstractEventConsumer(String topic) {
	this.topic = EventCenter.topic(topic);
	deserializer = Deserializer.getDefault();
    }

    @Override
    public String getTopic() {
	return topic.name;
    }

    Topic topic() {
	return topic;
    }

    @Override
    public Deserializer getDeserializer() {
	return deserializer;
    }

    @Override
    public void setDeserializer(Deserializer deserializer) {
	this.deserializer = deserializer != null ? deserializer : Deserializer.getDefault();
    }

}
