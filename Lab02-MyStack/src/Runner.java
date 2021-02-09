import java.util.Stack;

public class Runner {

  public static Stack<Integer> makeStack(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    for (int num : nums)
      stack.push(num);
    return stack;
  }
  
}