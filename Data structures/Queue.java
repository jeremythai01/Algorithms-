public class Queue {
    private int size;
    private Node first;
    private Node last;

    private class Node{
        private int value;
        private Node next;

        public Node(Node next, int value){
            this.value = value;
            this.next = next;
        }
    }

    public Queue() {
        this.size = 0;
        this.last = null;
        this.first = null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void enqueue(int element) {

        if (isEmpty()) {
            first = new Node(null, element);
            last = first;
        } else {
            Node oldLast = last;
            last = new Node(null, element);
            oldLast.next = last;
        }
        size++;
    }

    public int dequeue(){
        if (isEmpty())
            return -1; // ERROR
        int deleted = first.value;
        first = first.next;
        if (size == 1)
            last = first;
        size--;
        return deleted;
    }

    public String toString(){
        String output = "[ ";
        Node temp = first;
        while (temp!= null){
            output += temp.value +" ";
            temp = temp.next;
        }
        return output + "]";
    }
}
