package com.barakugav.emagnetar;

import java.util.Objects;
import java.util.function.Function;

public class Pipeline {

    private final Topic topic;
    private final Function<? super Event, ? extends Event> pipe;
    private Serializer serializer;
    private Deserializer deserializer;

    Pipeline(String destinationTopic, Function<? super Event, ? extends Event> pipe) {
	this.topic = EMagnetar.topic(destinationTopic);
	this.pipe = Objects.requireNonNull(pipe);
	serializer = Serializer.getDefault();
	deserializer = Deserializer.getDefault();
    }

    void pipe(EventRecord eventRecord) {
	Event event = Event.valueOf(eventRecord, deserializer);
	Event fEvent = pipe.apply(event);
	EventRecord fEventRecord = EventRecord.valueOf(fEvent, serializer);
	topic.postEventRecord(fEventRecord);
    }

    public String getDestinationTopic() {
	return topic.getName();
    }

    public Serializer getSerializer() {
	return serializer;
    }

    public void setSerializer(Serializer serializer) {
	this.serializer = serializer != null ? serializer : Serializer.getDefault();
    }

    public Deserializer getDeserializer() {
	return deserializer;
    }

    public void setDeserializer(Deserializer deserializer) {
	this.deserializer = deserializer != null ? deserializer : Deserializer.getDefault();
    }

}
