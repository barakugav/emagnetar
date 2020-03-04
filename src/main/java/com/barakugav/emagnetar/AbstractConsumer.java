package com.barakugav.emagnetar;

abstract class AbstractConsumer implements Consumer {

    private Deserializer deserializer;

    AbstractConsumer() {
	deserializer = Deserializer.getDefault();
    }

    @Override
    public Deserializer getDeserializer() {
	return deserializer;
    }

    @Override
    public void setDeserializer(Deserializer deserializer) {
	this.deserializer = deserializer != null ? deserializer : Deserializer.getDefault();
    }

}
