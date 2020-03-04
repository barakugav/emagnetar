package com.barakugav.emagnetar;

abstract class AbstractProducer implements Producer {

    private Serializer serializer;

    AbstractProducer() {
	serializer = Serializer.getDefault();
    }

    @Override
    public Serializer getSerializer() {
	return serializer;
    }

    @Override
    public void setSerializer(Serializer serializer) {
	this.serializer = serializer != null ? serializer : Serializer.getDefault();
    }

}
