public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    // len = 10, first = 8, last = 5

    private void changeSize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if (size > 0) {
            if (first <= last) {
                System.arraycopy(items, first, temp, 0, last - first + 1);
            } else {
                System.arraycopy(items, first, temp, 0, items.length - first);
                System.arraycopy(items, 0, temp, items.length - first, last + 1);
            }
            first = 0;
            last = size - 1;
        } else {
            first = 0;
            last = 0;
            size = 0;
        }
        items = temp;
    }

    private void increSize() {
        changeSize(items.length * 2);
    }

    private void decreSize() {
        changeSize(items.length / 2);
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        if (first <= last) {
            for (int i = first; i <= last; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = first; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i <= last; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public void addFirst(T item) {
        if (size == items.length) {
            increSize();
        }
        if (size == 0) {
            items[first] = item;
            size++;
        } else if (first == 0) {
            first = items.length - 1;
            items[first] = item;
            size++;
        } else {
            first -= 1;
            items[first] = item;
            size++;
        }
    }

    public void addLast(T item) {
        if (size == items.length) {
            increSize();
        }
        if (size == 0) {
            items[last] = item;
            size++;
        } else if (last == items.length - 1) {
            last = 0;
            items[last] = item;
            size++;
        } else {
            last += 1;
            items[last] = item;
            size++;
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T itemToRemove = items[first];
        items[first] = null;
        if (first != items.length - 1) {
            first++;
        } else {
            first = 0;
        }
        size--;
        if (items.length > 8 && size < items.length / 4) {
            decreSize();
        }
        if (size == 0) {
            first = 0;
            last = 0;
        }
        return itemToRemove;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T itemToRemove = items[last];
        items[last] = null;
        if (last != 0) {
            last--;
        } else {
            last = items.length - 1;
        }
        size--;
        if (items.length > 8 && size < items.length / 4) {
            decreSize();
        }
        if (size == 0) {
            first = 0;
            last = 0;
        }
        return itemToRemove;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int cur = first;
        for (int i = 0; i < index; i++) {
            cur += 1;
            if (cur == items.length) {
                cur = 0;
            }
        }
        return items[cur];
    }
}
