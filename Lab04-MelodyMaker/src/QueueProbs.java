import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {
  public Queue<Integer> evenFirst(Queue<Integer> nums)
  {
    Queue<Integer> odds = new LinkedList<>();
    Queue<Integer> evens = new LinkedList<>();

    while(nums.peek() != null) {
      boolean success = (nums.peek() % 2 == 0) ? evens.offer(nums.poll()) : odds.offer(nums.poll());
    }
    
    while(evens.peek() != null) {
      nums.offer(evens.poll());
    }
    
    while(odds.peek() != null) {
      nums.offer(odds.poll());
    }

    return nums;
  }

  public ArrayList<Integer> primeToN(int n) {
    Queue<Integer> store = new LinkedList<>();
    Stack<Integer> primes = new Stack<>();
    // TODO

    return null;
  }

  /**
   * Unit Test
   * @param args
   */
  public static void main(String[] args) {
    try {
      QueueProbs prob = new QueueProbs();
      assert prob.evenFirst(toLinkedList(3, 5, 4, 17)).equals(toLinkedList(4, 3, 5, 17)) : "at evenFirst()";
      assert prob.primeToN(10).equals(toArrayList(1, 3, 5, 7)) : "at primeToN()";
    }
    catch(AssertionError e) { System.err.println("Unit Test Failed"); }
    finally { System.out.println("Unit Test Successed"); }
  }

  public static <T> LinkedList<T> toLinkedList(T ...args) {
    LinkedList<T> l = new LinkedList<>();
    for(T arg: args)
      l.offer(arg);
    return l;
  }

  public static <T> ArrayList<T> toArrayList(T ...args) {
    ArrayList<T> a = new ArrayList<>();
    for(T arg: args) 
      a.add(arg);
    return a;
  }

  

}