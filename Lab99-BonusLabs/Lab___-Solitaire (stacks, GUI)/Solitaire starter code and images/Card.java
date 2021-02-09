/**
  * This class represents a playing card, with suits "h", "d", "c", "s"
  * and ranks 1 (ace), 2 (two) ... 13 (king)
  */
public class Card
{
  //THE CONSTANTS THAT REPRESENT EACH SUIT
  public static final String HEARTS = "h";
	public static final String DIAMONDS = "d";
	public static final String CLUBS = "c";
	public static final String SPADES = "s";
  public static final String[] SUITS = {HEARTS, DIAMONDS, CLUBS, SPADES};

  private boolean isFaceUp;
  private int rank;
  private String suit;

  public Card(int rank, String suit)
  {
    this.rank = rank;
    this.suit = suit;
    isFaceUp = false;
  }

  public int getRank()
  {
    return rank;
  }

  public String getSuit()
  {
    return suit;
  }

  public boolean isFaceUp()
  {
    return isFaceUp;
  }

  public void turnUp()
  {
    isFaceUp = true;
  }

  public void turnDown()
  {
    isFaceUp = false;
  }

  public String getFileName()
  {
    if (isFaceUp)
      return rank + suit + ".gif";
    else
      return "back.gif";
  }
}
