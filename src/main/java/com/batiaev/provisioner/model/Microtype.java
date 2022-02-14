package com.batiaev.provisioner.model;

import java.util.Objects;

import static java.lang.String.format;

public abstract class Microtype<T> {
    private final T value;

    protected Microtype(T value) {
        this.value = validate(notNull(value));
    }

    protected T notNull(T value) {
        if (value == null)
            throw new IllegalArgumentException(format("%s should not be null", getClass().getSimpleName()));
        return value;
    }

    protected T validate(T value) {
        return value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Microtype<?> microtype = (Microtype<?>) o;
        return Objects.equals(value, microtype.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return getClass() + "(" + value + ')';
    }
}
