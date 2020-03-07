package com.barakugav.emagnetar;

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

}
