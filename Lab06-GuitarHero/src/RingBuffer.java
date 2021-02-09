import java.util.Arrays;
import java.util.function.Function;

public class RingBuffer implements Cloneable
{


    private double[] data;          // items in the buffer
    private int      first;         // index for the next dequeue or peek
    private int      last;          // index for the next enqueue
    private int      size;          // number of items in the buffer

    /** create an empty buffer, with given max capacity */
    public RingBuffer(int capacity) {
        data = new double[capacity];
        size = 0;
        first = 0;
        last = 0;
    }

    /** return number of items currently in the buffer */
    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    /** is the buffer empty (size equals zero)? */
    public boolean isEmpty() {
        return size == 0;
    }

    /** is the buffer full (size equals array capacity)? */
    public boolean isFull() {
        return size == data.length;
    }

    /** add item x to the end */
    public void enqueue(double x) throws RuntimeException {
        if(isFull()) throw new RuntimeException();
        data[last] = x;
        last++;
        if(last == data.length) last = 0;
        size++;
    }

    @Override
    public String toString() {
        String s = "[";
        for(int i = 0 ; i < data.length ; i++) s += data[i] + ", ";
        s += "]" + "\n" + "First: " + first + " | " + "Last: " + last;
        return s;
    }

    /** delete and return item from the front */
    public double dequeue() throws RuntimeException {
        if(isEmpty()) throw new RuntimeException();
        double d = peek();
        data[first] = 0;
        first++;
        if(first == data.length)
            first = 0;
        size--;
        return d;
        
    }

    /** return (but do not delete) item from the front */
    public double peek() {
        return data[first];
    }

    /** a simple test of the constructor and methods in RingBuffer */
    public static void main(String[] args) {
        int N = 100;
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
            System.out.println(buffer.size);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());
        
        /*
         * Your program should produce the following output:
         * 
         *  Size after wrap-around is 100
			5050.0
         */
    }
}
