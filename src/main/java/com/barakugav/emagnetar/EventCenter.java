package com.barakugav.emagnetar;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class EventCenter {

    private final Map<String, Topic> topics;

    private static final EventCenter INSTANCE = new EventCenter();

    private EventCenter() {
	topics = new ConcurrentHashMap<>();
    }

    public static EventProducer newProducer(String topic) {
	return new DefaultEventProducer(topic);
    }

    public static EventCunsumer newCunsumer(String topic) {
	return new DefaultEventCunsumer(topic);
    }

    static Topic topic(String name) {
	return INSTANCE.topics.computeIfAbsent(Objects.requireNonNull(name), Topic::new);
    }

}
