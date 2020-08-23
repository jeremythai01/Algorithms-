public class Stack {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int[] array;

    public Stack(){
        this.array = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    private void resize(int size){
        int[] temp = new int[size];
        for (int i = 0; i < this.size ; i++){
            temp[i] = array[i];
        }
        array = temp;
    }

    public void push(int value){
        if (this.size == array.length)
            resize(2*array.length);
        array[this.size++] = value;
    }

    public int pop() {
        int deleted = array[--size];
        array[size] = null; //avoid loitering

        if (size > 0 && size == array.length/4)
            resize(array.length/2);
        return deleted;
    }

    public String toString(){

        String output = "[ ";
        for (int element : array){
            output += element + " ";
        }
        return output + "]";
    }
}
