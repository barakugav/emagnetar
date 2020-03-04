package com.barakugav.emagnetar;

abstract class AbstractProducer implements Producer {

    private final Topic topic;
    private Serializer serializer;

    AbstractProducer(String topic) {
	this.topic = EMagnetar.topic(topic);
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
