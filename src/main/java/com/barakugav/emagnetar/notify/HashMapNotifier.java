package com.barakugav.emagnetar.notify;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.barakugav.util.Holder;

public class HashMapNotifier extends AbstractNotifier {

    private final Map<Object, List<Listener<?>>> listenMap;

    public HashMapNotifier() {
	listenMap = new ConcurrentHashMap<>(0);
    }

    @Override
    public boolean add(Object key, Listener<?> listener) {
	Objects.requireNonNull(listener);
	Holder.Boolean changed = new Holder.Boolean();
	listenMap.compute(key, (k, listenList) -> {
	    if (listenList == null)
		listenList = new CopyOnWriteArrayList<>();
	    changed.set(listenList.add(listener));
	    return listenList;
	});
	return changed.getBoolean();
    }

    @Override
    public boolean remove(Object key, Listener<?> listener) {
	if (listener == null)
	    return false;
	Holder.Boolean changed = new Holder.Boolean();
	listenMap.compute(key, (k, listenList) -> {
	    changed.set(listenList != null && listenList.remove(listener));
	    return listenList.isEmpty() ? null : listenList;
	});
	return changed.getBoolean();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void notify(Object key, Object event) {
	Objects.requireNonNull(event);
	List<Listener<?>> listenList = listenMap.get(key);
	if (listenList != null)
	    for (Listener listener : listenList)
		listener.notify(event);
    }

    @Override
    public void clear() {
	// Help GC
	for (List<?> listenersList : listenMap.values())
	    listenersList.clear();
	listenMap.clear();
    }

    @Override
    public void clear(Object key) {
	List<Listener<?>> listenList = listenMap.remove(key);
	if (listenList != null)
	    listenList.clear(); // Help GC
    }

}
