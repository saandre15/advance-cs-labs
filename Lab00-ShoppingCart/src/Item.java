import java.lang.*;


public class Item {

  private String name;
  private double price;
  private boolean hasBulkDiscount;
  private int bulkQty;
  private double bulkPrice;
  
  public Item(String name, double price) throws IllegalArgumentException {
    if(price < 0)
      throw new IllegalArgumentException("Invalid");
    this.name = name;
    this.price = price;
    this.hasBulkDiscount = false;
    this.bulkPrice = 0;
    this.bulkQty = 0;
  }

  public Item(String name, double price, int bulkQty, double bulkPrice) throws IllegalArgumentException {
    this(name, price);
    if(bulkQty < 0 || bulkPrice < 0)
      throw new IllegalArgumentException("Invalid");
    this.hasBulkDiscount = true;
    this.bulkPrice = bulkPrice;
    this.bulkQty = bulkQty;
  }

  public double priceFor(int quantity) {
    return hasBulkDiscount && quantity >= bulkQty ? quantity * bulkPrice : quantity * price;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Item) {
      Item item = (Item)obj;
      if(item.name == name)
        return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return name + ", $" + price + (hasBulkDiscount ? " (" + bulkQty + " for " + bulkPrice + ")" : "");
  }

  
}