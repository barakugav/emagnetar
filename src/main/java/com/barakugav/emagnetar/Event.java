package com.barakugav.emagnetar;

public final class Event {

    private final String key;
    private final Object data;

    Event(String key, Object data) {
	this.key = key;
	this.data = data;
    }

    public String getKey() {
	return key;
    }

    public Object getData() {
	return data;
    }

    static Event valueOf(EventRecord record, Deserializer deserializer) {
	String key = record.key;
	Object data;
	try {
	    data = deserializer.deserialize(record.data);
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException(e);
	}
	return new Event(key, data);
    }

}
