package src;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Vector;
import java.util.stream.IntStream;

public class MyStack<T>  {

  private T[] stack;
  private int size;
  
  public MyStack() {
    this(10);
  }

  public MyStack(int initCap) {
    stack = (T[])(new Object[initCap]);
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public T peek() {
    if(size < 1) throw new EmptyStackException();
    return stack[size - 1];
  }

  public T pop() {
    T num = peek();
    stack[size - 1] = null;
    size--;
    return num;
  }

  public void push(T item) {
    if(stack.length - 1 < size + 1) doubleCapacity();
    stack[size] = item;
    size++;
  }

  private void doubleCapacity() {
    T[] next = (T[])new Object[stack.length * 2];
    for(int i = 0 ; i < stack.length ; i++) {
      next[i] = stack[i];
    }
    stack = next;
  }

  @Override
  public String toString() {
    return "[" + IntStream
      .range(0, stack.length)
      .mapToObj(index -> stack[index].toString() + ", ")
      .reduce((total, cur) -> total + cur) + "]";
  }

}