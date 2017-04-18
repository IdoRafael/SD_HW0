package il.ac.technion.cs.sd.app;

import java.util.AbstractList;

public class ReversedArrayList<T> extends AbstractList<T> {
    T[] array;

    public ReversedArrayList(T[] array) {
        this.array = array;
    }

    @Override
    public T get(int index) {
        return array[array.length - index - 1];
    }

    @Override
    public int size() {
        return array.length;
    }
}
