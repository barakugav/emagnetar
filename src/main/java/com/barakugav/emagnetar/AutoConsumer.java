package com.barakugav.emagnetar;

public interface AutoConsumer extends Consumer {

    public void setAction(java.util.function.Consumer<Event> action);

    public void start();

    public void stop();

    public void setCyclePeriod(long period);
    
    public static AutoConsumer getDefault(String topic) {
	return new DefaultAutoConsumer(topic);
    }

}
