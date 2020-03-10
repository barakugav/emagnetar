package com.barakugav.emagnetar.notify;

public interface Notifier {

    public boolean addListener(Object key, Listener<?> listener);

    public boolean removeListener(Object key, Listener<?> listener);

    public void notifyListeners(Object key, Object event);

    public void clear();

}
