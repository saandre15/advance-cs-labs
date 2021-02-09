import java.util.ArrayList;
import java.util.Stack;

class SnowflakeProbs {

    public double sumReciprocals(int n) {
        return sumReciprocalsHelper(1, 1, n, 0);
    }
    private double sumReciprocalsHelper(int num, int denom, int target, int total) {
        if(denom == target) return total;
        total += (num / denom);
        return sumReciprocalsHelper(num, denom + 1, target, total);
    }

    public int productOfEvens(int n) {
        return productOfEvensHelper(1, n, 0);
    }
    private int productOfEvensHelper(int i, int target, int total) {
        if(i == target) return total;
        total += (i * 2);
        return productOfEvensHelper(i + 1, target, total);
    } 

    public void doubleUp(Stack<Integer> nums) {
        doubleUpHelper(nums, nums.size() * 2);
    }
    private void doubleUpHelper(Stack<Integer> source, int targetSize) {
        if(source.size() == targetSize) return;
        int data = source.remove(0);
        source.push(data);
        source.push(data);
    }

    public void countToBy(int n, int m) {
        if(n < 0) return;
        countToBy(n - m, m);
        System.out.println(n);
    }

    public int matchingDigits(int a, int b) {
        Stack<Integer> i = new Stack<Integer>();
        Stack<Integer> j = new Stack<Integer>();
        intToStack(i, j, a, b);
        return matchingDigitsHelper(i, j);
    }

    private void intToStack(Stack<Integer> i, Stack<Integer> j, int a, int b) {
        if(a == -1 && b == -1) return;
        if(a != -1) i.push(extractDigits(0, 1, a));
        if(b != -1) j.push(extractDigits(0, 1, b));
        intToStack(i, j, (a != -1 ? extractDigits(1, a) : -1), (b != -1 ? extractDigits(1, b) : -1));
    }

    private int extractDigits(int startIndex, int endIndex, int val) { 
        return Integer.valueOf(String.valueOf(val).substring(startIndex, endIndex));
    }

    private int extractDigits(int startIndex, int val) {
        return Integer.valueOf(String.valueOf(val).substring(startIndex));
    }


    private int matchingDigitsHelper(Stack<Integer> i, Stack<Integer> j)
    {
        return matchingDigitsHelper(i, j.pop()) + matchingDigitsHelper(i, j);
    }


    private int matchingDigitsHelper(Stack<Integer> i, int j) {
        if(!i.empty()) return 0;
        int num = i.pop();
        if(num == j) return 1;
        int match = matchingDigitsHelper(i, j);
        i.push(num);
        return match;
    }

    public void printThis(int n) {
        ArrayList<String> s = new ArrayList<>();
        s.add(""); s.add("");
        printThisHelper(n, s);
        System.out.println(s.get(0) + (n % 2 == 0 ? "**" : "*")  + s.get(1));
    }

    public void printThisHelper(int n, ArrayList<String> s) {
       if(n < 0) return;
       s.set(0, "<" + s.get(1)); s.set(1, s.get(1) + ">");
       printThisHelper(n - 2, s);
    }

    public void printNums2(int n) {
        System.out.println(printNums2Helper(n));
    }

    private String printNums2Helper(int n) {
        if(n == 1) return "1";
        if(n == 2) return "11";
        return (n % 2 == 0 ? n / 2 : (n + 1) / 2) + printNums2Helper(n - 2) + (n % 2 == 0 ? n / 2 : (n + 1) / 2);
    }
}