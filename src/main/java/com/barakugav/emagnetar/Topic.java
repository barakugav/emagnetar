package com.barakugav.emagnetar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

class Topic {

    private final String name;
    private final List<EventRecord> eventsRecords;
    private final List<Pipeline> pipelines;

    Topic(String name) {
	this.name = Objects.requireNonNull(name);
	eventsRecords = new ArrayList<>();
	pipelines = new CopyOnWriteArrayList<>();
    }

    String getName() {
	return name;
    }

    void registerPipeline(Pipeline pipeline) {
	pipelines.add(Objects.requireNonNull(pipeline));
    }

    EventRecord getEventRecord(int curser) {
	synchronized (eventsRecords) {
	    if (curser < eventsRecords.size())
		return eventsRecords.get(curser);
	    else
		return null;
	}
    }

    boolean postEventRecord(EventRecord eventRecord) {
	Objects.requireNonNull(eventRecord);
	synchronized (eventsRecords) {
	    eventsRecords.add(eventRecord);
	}
	for (Pipeline pipeline : pipelines)
	    pipeline.pipe(eventRecord);
	return true;
    }

    @Override
    public String toString() {
	return "Topic[" + name + "]=>" + eventsRecords;
    }

}
