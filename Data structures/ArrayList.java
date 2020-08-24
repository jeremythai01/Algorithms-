public class ArrayList {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private int[] elements;

    public ArrayList() {
        this.size = 0;
        elements = new int[DEFAULT_CAPACITY];
    }


    public void ensureCapacity(int newCapacity) {

        if (newCapacity < size)
            return;

        int[] old = elements;
        elements = new int[newCapacity];

        for (int i = 0; i < size(); i++) {
            elements[i] = old[i];
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Integer get(int index) {
        if (index < 0 || index >= size)
            return null;

        return elements[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size)
            return;
        elements[index] = value;
    }


    public void addLast(int value) {
        add(size(), value);
    }

    public void addFirst(int value) {
        add(0, value);
    }


    public void add(int index, int value) {
        if (elements.length == size)
            ensureCapacity(size() * 2 + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
        size++;
    }

    public int remove(int index) {
        int deleted = elements[index];
        for (int i = index; i < size() - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return deleted;
    }
}