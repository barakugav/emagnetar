package com.barakugav.emagnetar;

import java.util.Arrays;
import java.util.Objects;

final class EventRecord {

    final String key;
    final byte[] data;

    EventRecord(String key, byte[] data) {
	this.key = key;
	this.data = data;
    }

    static EventRecord valueOf(Event event, Serializer serializer) {
	String key = event.getKey();
	byte[] data = serializer.serialize(event.getData());
	return new EventRecord(key, data);
    }

    @Override
    public boolean equals(Object other) {
	if (other == this)
	    return true;
	if (!(other instanceof EventRecord))
	    return false;

	EventRecord o = (EventRecord) other;
	return Objects.equals(o.key, key) && Arrays.equals(o.data, data);
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(key) ^ Arrays.hashCode(data);
    }

    @Override
    public String toString() {
	return key + "->" + data;
    }

}
