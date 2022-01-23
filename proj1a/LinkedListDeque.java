public class LinkedListDeque<T> {
    private ListNode continel;
    private int size;

    private class ListNode {
        private T item;
        private ListNode prev;
        private ListNode next;

        public ListNode(T t, ListNode p, ListNode n) {
            item = t;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        continel = new ListNode(null, null, null);
        continel.next = continel;
        continel.prev = continel;
        size = 0;
    }

    public void addFirst(T item) {
        continel.next = new ListNode(item, continel, continel.next);
        continel.next.next.prev = continel.next;
        size += 1;
    }

    public void addLast(T item) {
        continel.prev = new ListNode(item, continel.prev, continel);
        continel.prev.prev.next = continel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            ListNode cur = continel;
            System.out.print(cur.next.item);
            for (int i = 0; i < size - 1; i++) {
                cur = cur.next;
                System.out.print(" " + cur.next.item);
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T ans = continel.next.item;
            continel.next = continel.next.next;
            continel.next.prev = continel;
            size -= 1;
            return ans;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T ans = continel.prev.item;
            continel.prev = continel.prev.prev;
            continel.prev.next = continel;
            size -= 1;
            return ans;
        }
    }

    public T get(int index) {
        if (index >= size - 1) {
            return null;
        } else {
            ListNode cur = continel;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur.item;
        }
    }

    private T getRecursiveHelper(int index, ListNode node) {
        if (index >= size) {
            return null;
        } else if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, continel.next);
    }
}
