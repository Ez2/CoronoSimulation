package nl.util;

import java.lang.reflect.Array;
import java.util.*;

public class SimpleList<E> extends AbstractList<E> {
    private E[] arr;
    private int index = -1;

    public SimpleList(Class<E> type, int size) {
        arr = (E[]) Array.newInstance(type, size);
    }

    public int getLastAddedIndex() {
        return index;
    }

    @Override
    public E get(int index) {
        return arr[index];
    }

    @Override
    public int size() {
        return index + 1;
    }

    @Override
    public boolean add(E element) {
        arr[++index] = element;
        return true;
    }

}
