package com.barakugav.emagnetar;

public interface Serializer {

    public byte[] serialize(Object obj);

    public static Serializer getDefault() {
	return DefaultSerializer.getInstance();
    }

}
