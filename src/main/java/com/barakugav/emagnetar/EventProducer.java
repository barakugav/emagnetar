package com.barakugav.emagnetar;

public interface EventProducer {

    public String getTopic();

    default void postEvent(String key, Object data) {
	postEvent(key, data, System.currentTimeMillis());
    }

    default void postEvent(String key, Object data, long version) {
	postEvent(new Event(key, data, version));
    }

    public void postEvent(Event event);

    public Serializer getSerializer();

    public void setSerializer(Serializer serializer);

    public static EventProducer newEventProducer(String topic) {
	return new DefaultEventProducer(topic);
    }

}
