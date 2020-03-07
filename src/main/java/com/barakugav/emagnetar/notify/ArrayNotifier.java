package com.barakugav.emagnetar.notify;

import java.util.Arrays;
import java.util.Objects;

public class ArrayNotifier implements Notifier {

    private Object[] keys;
    private Listener[] listeners;

    private int size;

    private static final Object[] EMPTY_KEYS_ARRAY = {};
    private static final Listener[] EMPTY_LISTENERS_ARRAY = {};

    public ArrayNotifier() {
	listeners = EMPTY_LISTENERS_ARRAY;
	keys = EMPTY_KEYS_ARRAY;
	size = 0;
    }

    @Override
    public synchronized boolean addListener(Object key, Listener listener) {
	Objects.requireNonNull(listener);

	ensureSize(size + 1);
	keys[size] = key;
	listeners[size] = listener;
	size++;

	return true;
    }

    @Override
    public synchronized boolean removeListener(Object key, Listener listener) {
	if (listener == null)
	    return false;
	int i;
	if (key != null) {
	    for (i = 0; i < size; i++)
		if (key.equals(keys[i]) && listener.equals(listeners[i]))
		    break;
	} else {
	    for (i = 0; i < size; i++)
		if (null == keys[i] && listener.equals(listeners[i]))
		    break;
	}
	if (i == size)
	    return false; // Not found

	keys[i] = keys[size - 1];
	keys[size - 1] = null;
	listeners[i] = listeners[size - 1];
	listeners[size - 1] = null;
	size--;
	return true;
    }

    @Override
    public void notifyListeners(Object key, NotifyEvent event) {
	Objects.requireNonNull(event);

	Object[] tempKeys;
	Listener[] tempList;
	synchronized (this) {
	    tempKeys = Arrays.copyOf(keys, size);
	    tempList = Arrays.copyOf(listeners, size);
	}

	int i;
	if (key != null) {
	    for (i = 0; i < tempKeys.length; i++)
		if (key.equals(tempKeys[i]))
		    tempList[i].notify(event);
	} else {
	    for (i = 0; i < tempKeys.length; i++)
		if (null == tempKeys[i])
		    tempList[i].notify(event);
	}
    }

    @Override
    public synchronized void clear() {
	for (int i = 0; i < size; i++) {
	    keys[i] = null;
	    listeners[i] = null;
	}
	size = 0;

    }

    private void ensureSize(int s) {
	if (listeners.length >= s)
	    return;
	int newL = Math.max(1, listeners.length * 2);
	listeners = Arrays.copyOf(listeners, newL, Listener[].class);
	keys = Arrays.copyOf(keys, newL, Object[].class);
    }

}
