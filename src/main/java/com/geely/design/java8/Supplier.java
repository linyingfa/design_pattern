package com.geely.design.java8;

/**
 * Created by xiaolin on 2018/12/8.
 */
public interface Supplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
