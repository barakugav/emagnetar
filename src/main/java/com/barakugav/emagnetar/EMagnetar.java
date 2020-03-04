package com.barakugav.emagnetar;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class EMagnetar {

    private final Map<String, Topic> topics;

    private static final EMagnetar INSTANCE = new EMagnetar();

    private EMagnetar() {
	topics = new ConcurrentHashMap<>();
    }

    public static Producer newProducer(String topic) {
	return new DefaultProducer(topic);
    }

    public static Consumer newConsumer(String topic) {
	return new DefaultConsumer(topic);
    }

    static Topic topic(String name) {
	return INSTANCE.topics.computeIfAbsent(Objects.requireNonNull(name), Topic::new);
    }

}
