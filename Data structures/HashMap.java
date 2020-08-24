public class HashMap<KeyType, DataType> {

    private static final int DEFAULT_CAPACITY = 20;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size;
    private int capacity;
    private final float loadFactor;

    private class Node<KeyType, DataType> {
        private final KeyType key;
        private DataType data;
        private Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        public Node(KeyType key, DataType data) {
            this.key = key;
            this.data = data;
            this.next = null;
        }
    }
    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY,
                DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        this.size = 0;
        this.capacity = initialCapacity;
        this.loadFactor = 1 / loadFactor;
        this.map = new Node[capacity];
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private int hash(KeyType key) {
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    private boolean needRehash() {
        return size * loadFactor > capacity;
    }

    private void rehash() {

        HashMap<KeyType, DataType> tempHashMap = new HashMap<>(capacity * CAPACITY_INCREASE_FACTOR);
        Node<KeyType, DataType> node;

        for (int i = 0; i < capacity; i++) {
            node = map[i];

            while (node != null) {
                tempHashMap.put(node.key, node.data);
                node = node.next;
            }
        }
        map = tempHashMap.map;
        capacity = tempHashMap.capacity;
    }

    public boolean containsKey(KeyType key) {
        return map[hash(key)] != null;
    }

    public DataType get(KeyType key) {
        int hashIndex = hash(key);
        Node<KeyType, DataType> currentNode = map[hashIndex];
        while (currentNode != null) {
            if (currentNode.key.equals(key))
                return currentNode.data;
            currentNode = currentNode.next;
        }
        return null;
    }

    public DataType put(KeyType key, DataType value) {

        Node<KeyType, DataType> nodeInput = new Node<>(key, value);
        int hashIndex = hash(key);

        if (!containsKey(key)) {
            this.map[hashIndex] = nodeInput;
            this.size++;
            if (needRehash())
                rehash();
            return null;
        }

        Node<KeyType, DataType> previousNode = null;
        Node<KeyType, DataType> currentNode = map[hashIndex];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                DataType oldValue = currentNode.data;
                currentNode.data = value;
                return oldValue;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        previousNode.next = nodeInput;
        size++;
        if (needRehash())
            rehash();
        return null;
    }

    public DataType remove(KeyType key) {

        int hashIndex = hash(key);

        Node<KeyType, DataType> nodeDelete = map[hashIndex];
        Node<KeyType, DataType> nodePrevious = null;

        while (nodeDelete != null) {
            if (nodeDelete.key.equals(key)) {
                DataType oldValue = nodeDelete.data;
                size--;
                if (nodePrevious == null)
                    map[hashIndex] = nodeDelete.next;
                else nodePrevious.next = nodeDelete.next;
                return oldValue;
            }
            nodePrevious = nodeDelete;
            nodeDelete = nodeDelete.next;
        }
        return null;
    }

}