import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Vector;
import java.util.stream.IntStream;

public class MyStack  {

  private Integer[] stack;
  private int size;

  public MyStack() {
    this(10);
  }

  public MyStack(int initCap) {
    stack = new Integer[initCap];
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Integer peek() {
    if(size < 1) throw new EmptyStackException();
    return stack[size - 1];
  }

  public Integer pop() {
    int num = peek();
    stack[size - 1] = 0;
    size--;
    return num;
  }

  public void push(Integer item) {
    if(stack.length - 1 < size + 1) doubleCapacity();
    stack[size] = item;
    size++;
  }

  private void doubleCapacity() {
    Integer[] next = new Integer[stack.length * 2];
    for(int i = 0 ; i < stack.length ; i++) {
      next[i] = stack[i];
    }
    stack = next;
  }

  @Override
  public String toString() {
    return "[" + IntStream
      .range(0, stack.length)
      .map(index -> stack[index].intValue())
      .mapToObj(cur -> cur + ", ")
      .reduce((total, cur) -> total + cur) + "]";
  }

}
