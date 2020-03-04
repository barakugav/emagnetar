package com.barakugav.emagnetar;

public interface Cunsumer {

    public String getTopic();

    public Event nextEvent();

    public Deserializer getDeserializer();

    public void setDeserializer(Deserializer deserializer);

    public static Cunsumer newEventCunsumer(String topic) {
	return new DefaultCunsumer(topic);
    }

}
