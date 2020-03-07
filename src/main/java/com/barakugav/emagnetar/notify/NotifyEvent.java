package com.barakugav.emagnetar.notify;

public class NotifyEvent {

    private final Object source;
    private Object data;

    public NotifyEvent(Object source) {
	this.source = source;
    }

    public NotifyEvent(Object source, Object data) {
	this.source = source;
	this.data = data;
    }

    public Object getSource() {
	return source;
    }

    public Object getData() {
	return data;
    }

    public Object setData(Object newData) {
	Object tempData = data;
	data = newData;
	return tempData;
    }

    @Override
    public String toString() {
	return super.toString() + ", source: " + source + ", data: " + data;
    }

}
