public class MyLinkedList<T> {
  
  private ListNode<T> head;

  private class ListNode<T> {
  
    private T val;
    private ListNode<T> next;
    
    public ListNode(T val) { this.val = val; }
    
    @Override
    public String toString() {
      return "" + this.val;
    }

    private boolean hasNext() {
      return next != null;
    }
    
  }

  public MyLinkedList() {
    head = null;
  }

  public MyLinkedList(T val) {
    head = new ListNode<>(val);
  }

  public MyLinkedList(T... vals) {
    this(vals[0]);
    for(T val: vals) add(val);
  }

  public void add(T val) {
    if(head == null) {
      head = new ListNode<>(val);
      return;
    }
    ListNode<T> cur = head;
    while(cur != null) {
      if(cur.next == null) {
        cur.next = new ListNode<>(val);
        return;
      }
      cur = cur.next;
    };
  }

  public boolean contains(int target) {
    ListNode<T> cur = head.next;
    while(cur != null) {
      if(cur.val.equals(target)) return true;
      cur = cur.next;
    }
    return false;
  }

  public T get(int index) throws IndexOutOfBoundsException { 
    return getNode(index).val; 
  }
  

  private ListNode<T> getNode(int index) throws IndexOutOfBoundsException {
    if(index < 0) throw new IndexOutOfBoundsException();
    int counter = 0;
    ListNode<T> cur = head;
    while(cur != null) {
      if(counter == index) 
        return cur;
      counter++;
      cur = cur.next;
    }
    throw new IndexOutOfBoundsException();
  }

  public int indexOf(T target) {
    ListNode<T> cur = head;
    int counter = 0;
    while(cur != null) {
      if(target.equals(cur.val))
        return counter;
      cur = cur.next;
      counter++;
    }
    return -1;
  }

  public void set(T val, int index) throws IndexOutOfBoundsException {
    getNode(index).val = val;
  }

  public int size() {
    if(isEmpty()) return 0;
    int counter = 0;
    ListNode<T> cur = head;
    while(cur != null) {
      cur = cur.next;
      counter++;
    }
    return counter;
  }

  public int sizeRecursive() {
    return sizeRecursiveHelper(0, head);
  }

  private int sizeRecursiveHelper(int total, ListNode<T> node) {
    if(node.next == null)
      return total;
    return sizeRecursiveHelper(total+1, node.next);
  }

  public boolean isEmpty() {
    return head == null;
  }

  public T remove(int index) throws IndexOutOfBoundsException {
    if(index == 0) {
      ListNode<T> original = head;
      head = head.next;
      return original.val;
    }

    ListNode<T> n = getNode(index);
    getNode(index - 1).next = n.hasNext() ? n.next : null;
    return n.val;
  }

  public void add(T val, int index) {
    if(index == 0) {
      ListNode<T> second = getNode(index);
      head = new ListNode<>(val);
      head.next = second;
      return;
    }
    ListNode<T> first = getNode(index - 1);
    ListNode<T> second = new ListNode<>(val);
    ListNode<T> third = getNode(index);
    first.next = second;
    second.next = third;

  }
  
  @Override
  public String toString() {
    if(size() == 0)
      return "[]";
    String s = "[";
    ListNode<T> cur = head;
    while(cur != null) {
      s += cur.val + ", ";
      cur = cur.next;
    }
    return s + "]";
  }


}