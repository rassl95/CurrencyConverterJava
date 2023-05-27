package com.rassl.github.model;


import java.util.Objects;

/**
 * TODO Give me description
 * @param <T>
 * @param <K>
 */
public class Currency<T, K> {
    private T valuteName;
    private K valuteValue;

    public Currency(T valuteName, K valuteValue) {
        this.valuteName = valuteName;
        this.valuteValue = valuteValue;
    }

    public T getValuteName() {
        return valuteName;
    }


    public void setValuteValue(K valuteValue) {
        this.valuteValue = valuteValue;
    }

    public K getValuteValue() {
        return valuteValue;
    }

    public void setValuteName(T valuteName) {
        this.valuteName = valuteName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency<?, ?> currency = (Currency<?, ?>) o;
        return Objects.equals(getValuteName(), currency.getValuteName()) && Objects.equals(getValuteValue(), currency.getValuteValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValuteName(), getValuteValue());
    }
}
