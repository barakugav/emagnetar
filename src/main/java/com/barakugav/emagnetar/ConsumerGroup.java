package com.barakugav.emagnetar;

public interface ConsumerGroup {

    public String getTopic();

    public Consumer newConsumer();

    public static ConsumerGroup newConsumerGroup(String topic) {
	return new DefaultConsumerGroup(topic);
    }

}
