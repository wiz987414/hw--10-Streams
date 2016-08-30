package ru.sbt.streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamsImpl<T> {

    private final Collection<T> storedData;

    public StreamsImpl(Collection<? extends T> list) {
        this.storedData = new ArrayList<T>();
        this.storedData.addAll(list);
    }

    public static <T> StreamsImpl<T> of(Collection<? extends T> list) {
        return new StreamsImpl<>(list);
    }

    public StreamsImpl<T> filter(Predicate<? super T> predicate) {
            Collection<T> tempList = new ArrayList<>();
            tempList.addAll(this.storedData);
            for (T item : this.storedData) {
                if (!predicate.test(item)) tempList.remove(item);
            }
            this.storedData.clear();
            this.storedData.addAll(tempList);
            return this;
    }

    public StreamsImpl<T> testOut() {
        for (T item : this.storedData)
            System.out.println(item.toString());
        System.out.println("=========================================");
        return this;
    }

    public StreamsImpl<T> transform(Function<? super T, ? extends T> transformFunction) {
        Collection<T> tempList = new ArrayList<>();
        for (T item : this.storedData) tempList.add(transformFunction.apply(item));
        this.storedData.clear();
        this.storedData.addAll(tempList);
        return this;
    }

    public <KEY, VALUE> Map<Object, Object> toMap(Function<? super T, ? extends KEY> keyFunc, Function<? super T, ? extends VALUE> valueFunc) {
        Map<Object, Object> resultMap = new HashMap<>();
        for (T item : this.storedData) resultMap.put(keyFunc.apply(item), valueFunc.apply(item));
        return resultMap;
    }

    public T findItem(Object searchItem) {
        T findObject = null;
        for (T item : this.storedData)
            if (Objects.equals(item, searchItem))
                findObject = item;
        return findObject;
    }

    public List<T> toList() {
        List<T> resultList = new ArrayList<>();
        for (T item : this.storedData) resultList.add(item);
        return resultList;
    }
}