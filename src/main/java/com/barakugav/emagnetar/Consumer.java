package com.barakugav.emagnetar;

public interface Consumer {

    public String getTopic();

    public Event nextEvent();

    public Deserializer getDeserializer();

    public void setDeserializer(Deserializer deserializer);

    public static Consumer newConsumer(String topic) {
	return new DefaultConsumer(topic);
    }

}
