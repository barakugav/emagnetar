package com.barakugav.util;

public abstract class Holder<T> {

    Holder() {
    }

    public abstract T get();

    public abstract void set(T value);

    public static class Object<T> extends Holder<T> {

	private T value;

	public Object() {
	    value = null;
	}

	public Object(T value) {
	    this.value = value;
	}

	@Override
	public T get() {
	    return value;
	}

	@Override
	public void set(T value) {
	    this.value = value;
	}

    }

    public static class Boolean extends Holder<java.lang.Boolean> {

	private boolean value;

	public Boolean() {
	    value = false;
	}

	public Boolean(boolean value) {
	    this.value = value;
	}

	@Override
	public java.lang.Boolean get() {
	    return java.lang.Boolean.valueOf(getBoolean());
	}

	public boolean getBoolean() {
	    return value;
	}

	@Override
	public void set(java.lang.Boolean value) {
	    set(value != null ? value.booleanValue() : false);
	}

	public void set(boolean value) {
	    this.value = value;
	}

    }

    public static class Byte extends Holder<java.lang.Byte> {

	private byte value;

	public Byte() {
	    value = 0;
	}

	public Byte(byte value) {
	    this.value = value;
	}

	@Override
	public java.lang.Byte get() {
	    return java.lang.Byte.valueOf(getByte());
	}

	public byte getByte() {
	    return value;
	}

	@Override
	public void set(java.lang.Byte value) {
	    set(value != null ? value.byteValue() : 0);
	}

	public void set(byte value) {
	    this.value = value;
	}

    }

    public static class Short extends Holder<java.lang.Short> {

	private short value;

	public Short() {
	    value = 0;
	}

	public Short(short value) {
	    this.value = value;
	}

	@Override
	public java.lang.Short get() {
	    return java.lang.Short.valueOf(getShort());
	}

	public short getShort() {
	    return value;
	}

	@Override
	public void set(java.lang.Short value) {
	    set(value != null ? value.shortValue() : 0);
	}

	public void set(short value) {
	    this.value = value;
	}

    }

    public static class Int extends Holder<java.lang.Integer> {

	private int value;

	public Int() {
	    value = 0;
	}

	public Int(int value) {
	    this.value = value;
	}

	@Override
	public java.lang.Integer get() {
	    return java.lang.Integer.valueOf(getInt());
	}

	public int getInt() {
	    return value;
	}

	@Override
	public void set(java.lang.Integer value) {
	    set(value != null ? value.intValue() : 0);
	}

	public void set(int value) {
	    this.value = value;
	}

    }

    public static class Long extends Holder<java.lang.Long> {

	private long value;

	public Long() {
	    value = 0;
	}

	public Long(long value) {
	    this.value = value;
	}

	@Override
	public java.lang.Long get() {
	    return java.lang.Long.valueOf(getLong());
	}

	public long getLong() {
	    return value;
	}

	@Override
	public void set(java.lang.Long value) {
	    set(value != null ? value.longValue() : 0);
	}

	public void set(long value) {
	    this.value = value;
	}

    }

    public static class Float extends Holder<java.lang.Float> {

	private float value;

	public Float() {
	    value = 0;
	}

	public Float(float value) {
	    this.value = value;
	}

	@Override
	public java.lang.Float get() {
	    return java.lang.Float.valueOf(getFloat());
	}

	public float getFloat() {
	    return value;
	}

	@Override
	public void set(java.lang.Float value) {
	    set(value != null ? value.floatValue() : 0);
	}

	public void set(float value) {
	    this.value = value;
	}

    }

    public static class Double extends Holder<java.lang.Double> {

	private double value;

	public Double() {
	    value = 0;
	}

	public Double(double value) {
	    this.value = value;
	}

	@Override
	public java.lang.Double get() {
	    return java.lang.Double.valueOf(getDouble());
	}

	public double getDouble() {
	    return value;
	}

	@Override
	public void set(java.lang.Double value) {
	    set(value != null ? value.doubleValue() : 0);
	}

	public void set(double value) {
	    this.value = value;
	}

    }

    public static class Char extends Holder<java.lang.Character> {

	private char value;

	public Char() {
	    value = 0;
	}

	public Char(char value) {
	    this.value = value;
	}

	@Override
	public java.lang.Character get() {
	    return java.lang.Character.valueOf(getChar());
	}

	public char getChar() {
	    return value;
	}

	@Override
	public void set(java.lang.Character value) {
	    set(value != null ? value.charValue() : 0);
	}

	public void set(char value) {
	    this.value = value;
	}

    }

    public static class Void extends Holder<java.lang.Void> {

	public Void() {
	}

	@Override
	public java.lang.Void get() {
	    return null;
	}

	@Override
	public void set(java.lang.Void value) {
	    // void values, must be null
	}

    }

}
