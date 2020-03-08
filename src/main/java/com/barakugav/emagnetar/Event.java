package com.barakugav.emagnetar;

import java.util.Objects;

public final class Event {

    private final String key;
    private final Object data;

    public Event(String key, Object data) {
	this.key = key;
	this.data = data;
    }

    public String getKey() {
	return key;
    }

    public Object getData() {
	return data;
    }

    @Override
    public boolean equals(Object other) {
	if (other == this)
	    return true;
	if (!(other instanceof Event))
	    return false;

	Event o = (Event) other;
	return Objects.equals(o.key, key) && Objects.equals(o.data, data);
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(key) ^ Objects.hashCode(data);
    }

    @Override
    public String toString() {
	return key + "->" + data;
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
