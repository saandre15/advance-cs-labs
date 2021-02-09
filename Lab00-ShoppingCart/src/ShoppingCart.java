import java.util.ArrayList;

public class ShoppingCart {

  private ArrayList<ItemOrder> orders;
  
  public ShoppingCart()
  {
    this.orders = new ArrayList<>();
  }

  public void add(ItemOrder newOrder) {
    this.orders.add(newOrder);
  }

  public double getTotal()
  {
    return orders.stream()
      .mapToDouble(cur -> cur.getPrice())
      .sum();
  }

}