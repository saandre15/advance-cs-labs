import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import jdk.nashorn.internal.parser.Scanner;

public class WelcomeBack {

  public String getMiddle(String s) {
    return (s.length() % 2 == 0) 
      ? new StringBuilder()
        .append(s.charAt(s.length() / 2))
        .append(s.charAt((s.length() / 2) + 1 ))
        .toString() 
      : String.valueOf(s.charAt((int)Math.ceil(s.length() / 2))); 
  }

  public int[] sumNumbers(int n) {
    return IntStream.range(0, n)
      .map(num -> IntStream.range(0, num).reduce((cur, total) -> cur + total).orElseThrow(RuntimeException::new))
      .toArray();
  }

  public int sumDigits(int num) {
    return String
      .valueOf(num)
      .chars()
      .map(cur -> (char)cur - '0')
      .reduce((cur, total) -> cur + total)
      .orElseThrow(RuntimeException::new);
  }

  public int keepSummingDigits(int num) {
    return String.valueOf(num).length() == 1 ? num : keepSummingDigits(sumDigits(num));
  }

  public String getIntersection(int[] a, int[] b) {
    return a.length > b.length 
      ? getIntersection(b, a)
      : Arrays.stream(a)
      // Work on this
        .filter(num -> IntStream
          .range(0, b.length)
          .map(index -> b[index])
          .boxed()
          .collect(Collectors.toList())
          .contains(num))
        .mapToObj(num -> (char)(num + '0'))
        .map(num -> num.toString())
        .reduce((total, cur) -> total + cur)
        .orElseThrow(RuntimeException::new);
  }

  public Map<Integer, Integer> mapLengths(String[] words) {
    Map<Integer, Integer> map = new HashMap<>();
    Arrays.stream(words)
      .forEach(cur -> map.put(cur.length(), (map.get(cur.length()) == null ? 0 : map.get(cur.length())) + 1));
    return map;
  }

  public int sumDigitsRecur(int n) {
    return sumDigitsRecurHelper(n, 0);
  }

  private int sumDigitsRecurHelper(int n, int total) {
    return n == 0 
    ? total 
    : sumDigitsRecurHelper(
        Integer.parseInt(String.valueOf(n).length() > 1 ? String.valueOf(n).substring(1) : "0"), 
        total + (String.valueOf(n).charAt(0) - '0')
      );
  }

  private int charToInt(char c) {
    return c - '0';
  }

  public int sumWithoutCarry(int a, int b) {
    String strA = String.valueOf(a);
    String strB = String.valueOf(b);
    String strC = "";
    for(int i = 0 ; i < strA.length() ; i++) {
      String total = String.valueOf(charToInt(strA.charAt(i)) + charToInt(strB.charAt(i)));
      strC += total.charAt(total.length() - 1);
    }
    return Integer.parseInt(strC);
  }

  // Assuming all prices are postitive
  public int buySell1(int[] stock)
  {
    int start = 0;
    int end = stock.length;
    int maxProfit = 0; 
    while(start != end - 1) {
      for(int i = start ; i < end ; i++) {
        if(stock[i] -  stock[start] > maxProfit)
          maxProfit = stock[i] - stock[start];
      }
      start++;
    }

    return maxProfit;
  }

  public void zeck(String filename)
  {
    try {
      Scanner s = new Scanner(new File(filename));
      int[] store = new int[s.nextInt()];
      int counter = 0;
      while(s.hasNextInt()) {
        store[counter] = s.nextInt();
        counter++;
      }
      String print = "";
      for(int num: store) {
        int n = num;
        print += num + " = ";
        while(n > 0) {
          int fib = getSmallerFib(n);
          print += fib + " + ";
          n = n - fib;
        }
        print += "\n";
      }
      System.out.println(print);
      
      
    }
    catch(FileNotFoundException e) {
      System.err.println("Zeck file was not found!");
    }
    catch(IndexOutOfBoundsException e) {
      System.err.println("Zech scanner while loop failed");
    }
    
  }

  private int getSmallerFib(int num) {
    if (num == 0 || num == 1) 
    return num; 

    int f1 = 0, f2 = 1, f3 = 1; 
    while (f3 <= num) { 
        f1 = f2; 
        f2 = f3; 
        f3 = f1 + f2; 
    } 
    return f2; 
  }

}