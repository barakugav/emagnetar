package com.barakugav.emagnetar;

public interface Producer {

    public String getTopic();

    default boolean postEvent(String key, Object data) {
	return postEvent(key, data, System.currentTimeMillis());
    }

    default boolean postEvent(String key, Object data, long version) {
	return postEvent(new Event(key, data, version));
    }

    public boolean postEvent(Event event);

    public Serializer getSerializer();

    public void setSerializer(Serializer serializer);

    public static Producer newProducer(String topic) {
	return new DefaultProducer(topic);
    }

}
