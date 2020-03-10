package com.barakugav.emagnetar.notify;

@FunctionalInterface
public interface Listener<E> {

    public void notify(E e);

}
