import java.util.stream.IntStream;

class LinkedList<T> {
  
  private Node head;
  private int size;
  
  private class Node {
    private T data;
    private Node next;

    public Node(T data) {
      this.data = data;
    }
    
    public Node(T data, Node next) {
      this(data);
      this.next = next;
    }

    public T getData() {
      return data;
    }
    
    public Node getNext() {
      return next;
    }

    public boolean hasNext() {
      return next != null;
    }

    public Node setNext(Node node) {
      next = node;
      return this;
    }
  }

  public LinkedList() {
    this.head = null;
    this.size = 0;
  }

  public LinkedList(T data) {
    this();
    add(data);
  }

  public T indexOf(int index) throws IndexOutOfBoundsException {
    return getNode(index).data;
  }

  private Node getNode(int index) throws IndexOutOfBoundsException {
    if(index < 0 || size == 0) throw new IndexOutOfBoundsException();
    int counter = 0;
    Node cur = this.head;
    while(cur != null) {
      if(counter == index) return cur;
      cur = cur.next;
      counter++;
    }
    throw new IndexOutOfBoundsException();
  }

  public void add(T data, int index) {
    System.out.println(size);
    if(size == 0) this.head = new Node(data);
    else if(index == 0) head = new Node(data, head);
    else getNode(index-1)
      .setNext(new Node(data, getNode(index-1).hasNext() ? getNode(index) : null));
    this.size++;
  }

  public void add(T data) {
    add(data, size);
  }

  public T remove(int index) {
    size--;
    if(index == 0) {
      Node original = head;
      head = head.next;
      return original.data;
    }
    Node node = getNode(index);
    getNode(index - 1).next = node.hasNext() ? node.next : null;
    System.out.println(toString());
    return node.data;

    // T data = getNode(index).data;
    // if(index == 0) head = head.hasNext() ? getNode(index + 1) : null;
    // else getNode(index-1)
    //   .setNext(getNode(index).hasNext() ? getNode(index + 1) : null);
    // size--;
    // return data;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    if(size == 0) return "[]";
    return "[" + IntStream.range(0, size())
      .mapToObj(index -> indexOf(index))
      .map(cur -> cur.toString())
      .map(cur -> cur + ", ")
      .reduce((prev, cur) -> prev + cur)
      .orElseThrow(RuntimeException::new)
    + "]";
  }

  // UNIT TEST
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    System.out.println("Initial State");
    System.out.println(list);
    for(int i = 0 ; i < 4; i++) list.add(i);
    System.out.println("Added 4 items");
    System.out.println(list);
    assert list.indexOf(1).equals(1) : "failed @ add or indexOf";
    list.remove(1).equals(1);
    System.out.println(list);
    System.out.println("Removed 1 item at INDEX = 1");
    if(list.getNode(0).next.data.equals(2)) System.out.println("remove sanity check passed.");
    System.out.println(list);
    if( list.size() != 3) System.out.println("failed @ size");
  }
  
}