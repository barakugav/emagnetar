package com.barakugav.emagnetar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Topic {

    final String name;
    final List<EventRecord> eventsRecords;

    Topic(String name) {
	this.name = Objects.requireNonNull(name);
	eventsRecords = new ArrayList<>();
    }

}
