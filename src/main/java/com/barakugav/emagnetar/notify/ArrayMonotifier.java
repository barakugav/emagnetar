package com.barakugav.emagnetar.notify;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayMonotifier<E> implements Monotifier<E> {

    private final List<Listener<? super E>> listeners;

    public ArrayMonotifier() {
	listeners = new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean add(Listener<? super E> listener) {
	return listeners.add(Objects.requireNonNull(listener));
    }

    @Override
    public boolean remove(Listener<? super E> listener) {
	return listener != null && listeners.remove(listener);
    }

    @Override
    public void notify(E event) {
	Objects.requireNonNull(event);
	for (Listener<? super E> listener : listeners)
	    listener.notify(event);
    }

    @Override
    public void clear() {
	listeners.clear();
    }

}
