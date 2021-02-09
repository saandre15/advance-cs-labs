public class MyLinkedList {
  
  private ListNode head;

  private class ListNode {
  
    private int val;
    private ListNode next;
    
    public ListNode(int val) { this.val = val; }
    
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

  public MyLinkedList(int val) {
    head = new ListNode(val);
  }

  public void add(int val) {
    if(head == null) {
      head = new ListNode(val);
      return;
    }
    ListNode cur = head;
    while(cur != null) {
      if(cur.next == null) {
        cur.next = new ListNode(val);
        return;
      }
      cur = cur.next;
    };
  }

  public boolean contains(int target) {
    ListNode cur = head.next;
    while(cur != null) {
      if(cur.val == target) return true;
      cur = cur.next;
    }
    return false;
  }

  public int get(int index) throws IndexOutOfBoundsException { 
    return getNode(index).val; 
  }
  

  private ListNode getNode(int index) throws IndexOutOfBoundsException {
    if(index < 0) throw new IndexOutOfBoundsException();
    int counter = 0;
    ListNode cur = head;
    while(cur != null) {
      if(counter == index) 
        return cur;
      counter++;
      cur = cur.next;
    }
    throw new IndexOutOfBoundsException();
  }

  public int indexOf(int target) {
    ListNode cur = head;
    int counter = 0;
    while(cur != null) {
      if(target == cur.val)
        return counter;
      cur = cur.next;
      counter++;
    }
    return -1;
  }

  public void set(int val, int index) throws IndexOutOfBoundsException {
    getNode(index).val = val;
  }

  public int size() {
    if(isEmpty()) return 0;
    int counter = 0;
    ListNode cur = head;
    while(cur != null) {
      cur = cur.next;
      counter++;
    }
    return counter;
  }

  public int sizeRecursive() {
    return sizeRecursiveHelper(0, head);
  }

  private int sizeRecursiveHelper(int total, ListNode node) {
    if(node.next == null)
      return total;
    return sizeRecursiveHelper(total+1, node.next);
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int remove(int index) throws IndexOutOfBoundsException {
    if(index == 0) {
      ListNode original = head;
      head = head.next;
      return original.val;
    }

    ListNode n = getNode(index);
    getNode(index - 1).next = n.hasNext() ? n.next : null;
    return n.val;
  }

  public void add(int val, int index) {
    if(index == 0) {
      ListNode second = getNode(index);
      head = new ListNode(val);
      head.next = second;
      return;
    }
    ListNode first = getNode(index - 1);
    ListNode second = new ListNode(val);
    ListNode third = getNode(index);
    first.next = second;
    second.next = third;

  }
  
  @Override
  public String toString() {
    if(size() == 0)
      return "[]";
    String s = "[";
    ListNode cur = head;
    while(cur != null) {
      s += cur.val + ", ";
      cur = cur.next;
    }
    return s + "]";
  }

}