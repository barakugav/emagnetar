package com.barakugav.emagnetar.notify;

/**
 * 
 * @author Barak Ugav
 *
 */
public interface Notifier {

    public boolean add(Object key, Listener<?> listener);

    public boolean remove(Object key, Listener<?> listener);

    public void notify(Object key, Object event);

    public <E> Monotifier<E> monotifierView(Object key);

    public void clear();

    public void clear(Object key);

}
