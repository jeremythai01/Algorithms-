public class HashSet {

    private int size;
    private static int DEFAULT_CAPACITY = 10;
    private Node[] buckets;

    private class Node {
        private Node next;
        private int value;

        public Node (Node next, int value) {
            this.next = next;
            this.value = value;
        }
    }
    public HashSet(int capacity){
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    public HashSet(){
        this(DEFAULT_CAPACITY);
    }

    public int size(){
        return this.size;
    }

    private int hash(Integer value) {
        int index = value.hashCode() % this.buckets.length;
        return index;
    }

    public void add(Integer value) {
        int index = hash(value);
        System.out.println(index);
        if (buckets[index] == null)
            buckets[index] = new Node(null, value);
        else{
            Node temp = buckets[index];
            while (temp.next != null) {
                if (temp.value == value)
                    return;
                temp = temp.next;
            }
            if (temp.value != value){
                Node newNode = new Node(null, value);
                temp.next = newNode;
            }
        }
        this.size++;
    }

    public Integer remove(Integer value){
        int index = hash(value);

        if (buckets[index] == null)
            return null;

        if (buckets[index].value == value){
            int deleted = buckets[index].value;
            buckets[index] = buckets[index].next;
            this.size--;
            return deleted;
        }
        Node temp = buckets[index];
        while (temp.next != null){
            if (temp.next.value == value) {
                int deleted = temp.next.value;
                temp.next = temp.next.next;
                this.size--;
                return deleted;
            }
        }
        return null;
    }

    public String toString() {
        String output = "" ;

        for (Node element : buckets){
            if (element != null){
                Node temp = element;
                while (temp.next != null){
                    output += temp.value + " ";
                    System.out.println(temp.value);
                    temp = temp.next;
                }
                output += "\n";
            }
        }
        return output;
    }
}
