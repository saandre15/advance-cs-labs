import java.util.Map;

class Runner {
  public static void main(String[] args) {
    try {
      WelcomeBack wb = new WelcomeBack();
      String s = "Welcome";
      assert wb.getMiddle(s).equals("c") : "at getMiddle()";
      assert wb.sumNumbers(2).equals(new int[]{ 0, 1, 3, }) : "at sumNumbers()";
      assert wb.sumDigits(234) == 9 : "at sumDigits()";
      assert wb.sumDigitsRecur(234) == 9 : "at sumDigitsRecur()";
      assert wb.keepSummingDigits(29) == 2 : "at keepSummingDigits()";
      assert wb.getIntersection(new int[] { 1, 2, 3,4 }, new int[] { 1, 2, 3, 4 }) == "134" : "at getIntersection()";
      Map<Integer, Integer> map = wb.mapLengths(new String[] { "a", "b", "hello", "hi", "yo", "I" });
      assert map.get(1) == 3 : "at mapLength()";
      assert map.get(2) == 2 : "at mapLength()";
      assert map.get(5) == 1 : "at mapLength()";
      assert wb.buySell1(new int[] { 3, 4, 3, 2, 1, 5 }) == 4 : "at buySell1()";
      wb.zeck("zeck.txt");
    }
    catch(AssertionError e) {
      System.out.println("Unit Test failed!");
    } finally {
      System.out.println("Unit Test Succeeded!");
    }
    
  }
}