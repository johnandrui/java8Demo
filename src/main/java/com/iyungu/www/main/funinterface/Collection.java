package com.iyungu.www.main.funinterface;

import org.apache.poi.ss.formula.functions.T;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  18:41
 * @Modified By:
 */
public interface Collection extends java.util.Collection {
    default boolean removeIf2(Predicate<T> predicate) {
        boolean isRemoved = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                iterator.remove();
                isRemoved=true;
            }
        }
        return isRemoved;
    }
}
