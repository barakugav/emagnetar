package com.barakugav.emagnetar.notify;

public interface Monotifier<E> {

    public boolean add(Listener<? super E> listener);

    public boolean remove(Listener<? super E> listener);

    public void notify(E event);

    public void clear();

}
