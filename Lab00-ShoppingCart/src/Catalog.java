import java.util.ArrayList;

public class Catalog {
  
  private String name;
  private ArrayList<Item> items;
  
  public Catalog(String name) {
    this.name = name;
    this.items = new ArrayList<>();
  }

  public void add(Item item) {
    items.add(item);
  }

  public int size() {
    return items.size();
  }

  public Item get(int index) {
    return items.get(index);
  }

  public String getName() {
    return name;
  }
  
}