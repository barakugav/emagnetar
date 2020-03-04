package com.barakugav.emagnetar;

final class EventRecord {

    final String key;
    final byte[] data;
    final long version;

    EventRecord(String key, byte[] data, long version) {
	this.key = key;
	this.data = data;
	this.version = version;
    }

    static EventRecord valueOf(Event event, Serializer serializer) {
	String key = event.getKey();
	byte[] data = serializer.serialize(event.getData());
	long version = event.getVersion();
	return new EventRecord(key, data, version);
    }

}
