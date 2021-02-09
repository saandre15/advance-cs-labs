package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class FireProbs {
    
    public void printBinary(int digits)
    {
        printBinaryHelper(Integer.parseInt(IntStream.range(0, digits)
            .mapToObj(index -> "1")
            .reduce((prev, cur) -> prev + cur)
            .orElseThrow(RuntimeException::new), 2), 0);
    }

    private void printBinaryHelper(int target, int cur)
    {
        System.out.println(Integer.toBinaryString(cur));
        printBinaryHelper(target, cur + 1);
    }

    public void climbStairs(int steps)
    {
        
    }

    private void climbStairsHelper(int steps, int small, int big)
    {
        
    }

    public void campsite(int x, int y)
    {
        campsiteHelper(x, y, "", new Stack<>());
    }

    public void campsiteHelper(int curX, int curY, String direction, Stack<String> stack) {
        switch(direction) {
            case "S" -> stack.add("N");
            case "SW" -> stack.add("NE");
            case "W" -> stack.add("E");
        }
        if(
            (direction.equals("S") &&  curY == -1 ) || 
            (direction.equals("SW") && (curX == -1 ||  curY == -1)) ||
            (direction.equals("W") && (curX == -1)))  {
                return;
            }
        if(curX == 0 && curY == 0) System.out.println(stack);
        campsiteHelper(curX - 1, curY, "W", stack);
        stack.pop();
        campsiteHelper(curX - 1, curY - 1, "SW", stack);
        stack.pop();
        campsiteHelper(curX, curY - 1, "S", stack);
        stack.pop();
    }

    // public boolean campsiteHelper(int curX, int curY, String direction, LinkedList<Integer> list) {
    //     if(
    //         (direction.equals("S") &&  curY == -1 ) || 
    //         (direction.equals("SW") && (curX == -1 ||  curY == -1)) ||
    //         (direction.equals("W") && (curX == -1)))  {
    //             return false;
    //         }
    //     if(curX == 0 && curY == 0)  return true;
        
        
        
    //     // if(campsiteHelper(curX, curY - 1, "S")) System.out.print("N ");
        
    //     // if(campsiteHelper(curX - 1, curY - 1, "SW")) System.out.println("NE ");
    //     // if(campsiteHelper(curX - 1, curY, "W")) System.out.println("E ");
    //     // return true;
    // }


    // public int getMax(List<Integer> nums, int limit) {
        
    // }

    // public int makeChange(int amount) {
    //     ccce
    // }

    public static void main(String[] args) {
        FireProbs f = new FireProbs();
        f.campsite(2, 1);
    }

}
