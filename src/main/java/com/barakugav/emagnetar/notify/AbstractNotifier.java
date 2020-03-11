package com.barakugav.emagnetar.notify;

import java.util.Objects;

public abstract class AbstractNotifier implements Notifier {

    @Override
    public <E> Monotifier<E> monotifierView(Object key) {
	return new MonotifierView<>(this, key);
    }

    private static class MonotifierView<E> implements Monotifier<E> {

	private final Notifier notifier;
	private final Object key;

	MonotifierView(Notifier notifier, Object key) {
	    this.notifier = Objects.requireNonNull(notifier);
	    this.key = key;
	}

	@Override
	public boolean add(Listener<? super E> listener) {
	    return notifier.add(key, listener);
	}

	@Override
	public boolean remove(Listener<? super E> listener) {
	    return notifier.remove(key, listener);
	}

	@Override
	public void notify(E event) {
	    notifier.notify(key, event);
	}

	@Override
	public void clear() {
	    notifier.clear(key);
	}

    }

}
