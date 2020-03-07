package com.barakugav.emagnetar.notify;

@FunctionalInterface
public interface Listener {

    public void notify(NotifyEvent e);

}
