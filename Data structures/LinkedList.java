public class LinkedList {

    private int length;
    private Node head;

    private class Node {
        private Node next;
        private int value;

        public Node (Node next, int value) {
            this.next = next;
            this.value = value;
        }
    }

    public LinkedList (){
        this.head = null;
        this.length = 0;
    }

    public boolean isEmpty(){
        return this.length == 0;
    }

    public int size(){
        return this.length;
    }

    public void addLast(int value){
        if(this.length == 0){
            this.head = new Node(null, value);
        } else{
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(null, value);
        }
        this.length++;
    }

    public void add(int index, int value){

        if (this.length + 1 < index || index < 0)
            return;

        else if (this.length == 0) {
            this.head = new Node(null, value);
        }

        else {
            int counter = 1;
            Node temp = head;
            while (counter + 1 != index){
                temp = temp.next;
                counter++;
            }
            if(counter == this.length)
                temp.next = new Node(null, value);
            else{

                Node newNode = new Node(temp.next, value);
                temp.next = newNode;
            }
        }
        this.length++;
    }

    public void addFirst(int value){

        if (this.length == 0)
            head = new Node(null, value);
        else{
            Node temp = head;
            head = new Node(temp, value);
        }
        this.length++;
    }

    public void removeFirst(){

        if (this.length == 0)
            return;
        head = head.next;
        this.length--;
    }

    public void removeLast(){
        if (this.length == 0)
            return;

        if(this.length == 1)
            head = null;

        else {
            Node temp = head;
            while (temp.next.next != null){
                temp = temp.next;
            }
            temp.next = null;
        }
        this.length--;
    }

    public void remove(int index){
        if (this.length == 0 || this.length <= index || index < 0)
            return;

        if (this.length == 1)
            head = null;

        else {
            int counter = 0;
            Node temp = head;
            while (counter + 1 != index){
                temp = temp.next;
                counter++;
            }
            temp.next = temp.next.next;
        }
        this.length--;
    }

    public int get(int index) {
        if (this.length == 0 || this.length <= index) {
            System.out.println("Error");
            return -1;
        }
        int counter = 0;
        Node temp = head;
        while (counter != index){
            temp = temp.next;
        }
        return temp.value;
    }

    public void set(int index, int value){
        if (this.length == 0 || this.length <= index)
            return;

        int counter = 0;
        Node temp = head;
        while (counter != index){
            temp = temp.next;
        }
        temp.value = value;
    }

    public void toStrings(){
        Node temp = head;
        while (temp!= null){
            System.out.print(temp.value +"\t");
            temp = temp.next;
        }
        System.out.println("\n");
    }
}
