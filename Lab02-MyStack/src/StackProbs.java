import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

public class StackProbs {

  Stack<Integer> doubleUp(Stack<Integer> nums) {
    Stack<Integer> s = new Stack<>();
    while(nums.size() > 0) {
      int num = nums.remove(0);
      s.push(num);
      s.push(num);
    }
    return s;
  }

  Stack<Integer> posAndNeg(Stack<Integer> nums) {
    Stack<Integer> s = new Stack<>();
    while(nums.size() > 0) {
      int el = nums.pop();
      if(el < 0) {
        nums.insertElementAt(0, el);
      } else {
        nums.push(el);
      }
    }
    return s;
  }

  Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
    Stack<Integer> s = new Stack<>();
    for(int i = 0 ; i < n + 1 ; i++) {
      nums.push(nums.pop());
    }
    while(nums.size() > 0) {
      int el = nums.remove(0);
      s.push(el);
    }
    return s;
  }

  // String reverseVowels(String str) {
  //   Stack<String> strStack = (Stack<String>)new Vector(Arrays.asList(str.split("")));
  //   Stack<String> s = new Stack<>();
  //   String r = "";
  //   String replaceWith = new StringBuilder(str)
  //     .reverse()
  //     .chars()
  //     .mapToObj(num -> ((char)(num - '0')) + "")
  //     .filter(c -> isVowel(c))
  //     .reduce((total, cur) -> total + cur)
  //     .orElseThrow(RuntimeException::new);
  //   while(strStack.size() > 0) {
  //     String c = strStack.remove(0);
  //     isVowel(c) ? r += replaceWith
  //   }
  // }

  private boolean isVowel(String s) {
    return s == "a" || s == "e" || s == "i" || s =="o" || s == "u";
  }

  private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

  public boolean bracketBalance(String s) {
    Stack<Character> exectutionStack = new Stack<>();
    for(int i = 0 ; i < s.length() ; i++) {
      char c = s.charAt(i);
      if(c == '[' || c == '(' ) 
        exectutionStack.push(c);
      else if (c == ']' && exectutionStack.pop() != '[') 
          return false;
      else if (c == ')' || exectutionStack.pop() != '(') 
          return false;
    }
    return true;
  }

}