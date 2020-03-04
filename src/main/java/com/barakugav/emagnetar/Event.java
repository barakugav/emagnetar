package com.barakugav.emagnetar;

public final class Event {

    private final String key;
    private final Object data;
    private final long version;

    Event(String key, Object data, long version) {
	this.key = key;
	this.data = data;
	this.version = version;
    }

    public String getKey() {
	return key;
    }

    public Object getData() {
	return data;
    }

    public long getVersion() {
	return version;
    }

    static Event valueOf(EventRecord record, Deserializer deserializer) {
	String key = record.key;
	Object data;
	try {
	    data = deserializer.deserialize(record.data);
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException(e);
	}
	long version = record.version;
	return new Event(key, data, version);
    }

}
