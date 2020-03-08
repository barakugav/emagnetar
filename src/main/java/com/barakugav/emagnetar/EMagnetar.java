package com.barakugav.emagnetar;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

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

    public static Pipeline pipeline(String sourceTopic, String destinationTopic,
	    Function<? super Event, ? extends Event> pipe) {
	Objects.requireNonNull(sourceTopic);
	Objects.requireNonNull(destinationTopic);
	Objects.requireNonNull(pipe);
	if (sourceTopic.equals(destinationTopic))
	    throw new IllegalArgumentException();
	Pipeline pipeline = new Pipeline(destinationTopic, pipe);
	topic(sourceTopic).registerPipeline(pipeline);
	return pipeline;
    }

    static Topic topic(String name) {
	return INSTANCE.topics.computeIfAbsent(Objects.requireNonNull(name), Topic::new);
    }

}
