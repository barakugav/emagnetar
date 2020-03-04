package com.barakugav.emagnetar;

public interface Deserializer {

    public Object deserialize(byte[] bytes) throws ClassNotFoundException;

    public static Deserializer getDefault() {
	return DefaultDeserializer.getInstance();
    }

}
