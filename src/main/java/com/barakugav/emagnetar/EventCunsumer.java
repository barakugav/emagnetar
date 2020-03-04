package com.barakugav.emagnetar;

public interface EventCunsumer {

    public String getTopic();

    public Event nextEvent();

    public Deserializer getDeserializer();

    public void setDeserializer(Deserializer deserializer);

    public static EventCunsumer newEventCunsumer(String topic) {
	return new DefaultEventCunsumer(topic);
    }

}
