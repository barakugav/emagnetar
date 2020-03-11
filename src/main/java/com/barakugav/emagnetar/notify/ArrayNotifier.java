package com.barakugav.emagnetar.notify;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import com.barakugav.util.data.Pair;
import com.barakugav.util.data.Pairs;

public class ArrayNotifier extends AbstractNotifier {

    private final List<Pair<Object, Listener<?>>> listeners;

    public ArrayNotifier() {
	listeners = new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean add(Object key, Listener<?> listener) {
	Objects.requireNonNull(listener);
	return listeners.add(Pairs.unmodifiedPair(key, listener));
    }

    @Override
    public boolean remove(Object key, Listener<?> listener) {
	return listener != null && listeners.remove(Pairs.of(key, listener));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void notify(Object key, Object event) {
	Objects.requireNonNull(event);
	if (key != null) {
	    for (Pair<Object, Listener<?>> pair : listeners)
		if (key.equals(pair.getFirst()))
		    ((Listener) pair.getSecond()).notify(event);
	} else {
	    for (Pair<Object, Listener<?>> pair : listeners)
		if (null == pair.getFirst())
		    ((Listener) pair.getSecond()).notify(event);
	}
    }

    @Override
    public void clear() {
	listeners.clear();
    }

    @Override
    public void clear(Object key) {
	if (key != null)
	    listeners.removeIf(p -> key.equals(p.getFirst()));
	else
	    listeners.removeIf(p -> null == p.getFirst());
    }

}
