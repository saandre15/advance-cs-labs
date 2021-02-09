public class MyQueue<T> {
  
  private MyLinkedList<T> queue;

  public MyQueue(MyLinkedList<T> list) {
    queue = list;
  }
  
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public void offer(T item) {
    queue.add(item);
  }

  public T poll() {
    return queue.remove(0);
  }
  
  public int size() {
    return queue.size();
  }

  public void clear() {
    queue = new MyLinkedList<>();
  }

}
