import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class SolitaireDisplay extends JComponent implements MouseListener
{
  private static final int CARD_WIDTH = 71;
  private static final int CARD_HEIGHT = 96;
  private static final int SPACING = 5;  //distance between cards
  private static final int FACE_UP_OFFSET = 15;  //distance for cascading face-up cards
  private static final int FACE_DOWN_OFFSET = 5;  //distance for cascading face-down cards
  
  private JFrame frame;
  private int selectedRow = -1;
  private int selectedCol = -1;
  private Solitaire game;
  
  public SolitaireDisplay(Solitaire game)
  {
    this.game = game;
    
    frame = new JFrame("Solitaire");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(this);
    
    this.setPreferredSize(new Dimension(CARD_WIDTH * 7 + SPACING * 8, CARD_HEIGHT * 2 + SPACING * 3 + FACE_DOWN_OFFSET * 7 + 13 * FACE_UP_OFFSET));
    this.addMouseListener(this);
    
    frame.pack();
    frame.setVisible(true);
  }
  
  public void paintComponent(Graphics g)
  {
    //background
    g.setColor(new Color(0, 128, 0));
    g.fillRect(0, 0, getWidth(), getHeight());
    
    //face down
    drawCard(g, game.getStockCard(), SPACING, SPACING);
    
    //stock
    drawCard(g, game.getWasteCard(), SPACING * 2 + CARD_WIDTH, SPACING);
    if (selectedRow == 0 && selectedCol == 1)
      drawBorder(g, SPACING * 2 + CARD_WIDTH, SPACING);
    
    //aces
    for (int i = 0; i < 4; i++)
      drawCard(g, game.getFoundationCard(i), SPACING * (4 + i) + CARD_WIDTH * (3 + i), SPACING);
    
    //piles
    for (int i = 0; i < 7; i++)
    {
      Stack<Card> pile = game.getPile(i);
      drawPile(g, pile, i);
    }
  }
  
  //i is index of pile
  private void drawPile(Graphics g, Stack<Card> pile, int i)
  {
    int offset = 0;
    ArrayList<Card> temp = new ArrayList<Card>();
    while (!pile.isEmpty())
      temp.add(pile.pop());
    int j = 0;
    while (temp.size() > 0)
    {
      Card card = temp.remove(temp.size() - 1);
      pile.push(card);
      drawCard(g, card, SPACING + (CARD_WIDTH + SPACING) * i, CARD_HEIGHT + 2 * SPACING + offset);
      if (selectedRow == 1 && selectedCol == i && temp.size() == 0)
        drawBorder(g, SPACING + (CARD_WIDTH + SPACING) * i, CARD_HEIGHT + 2 * SPACING + offset);
      
      if (card.isFaceUp())
        offset += FACE_UP_OFFSET;
      else
        offset += FACE_DOWN_OFFSET;
      j++;
    }
  }
  
  private void drawCard(Graphics g, Card card, int x, int y)
  {
    if (card == null)
    {
      g.setColor(Color.BLACK);
      g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
    }
    else
    {
      String fileName = card.getFileName();
      if (!new File(fileName).exists())
        throw new IllegalArgumentException("bad file name:  " + fileName);
      Image image = new ImageIcon(fileName).getImage();
      g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);
    }
  }
  
  public void mouseExited(MouseEvent e)
  {
  }
  
  public void mouseEntered(MouseEvent e)
  {
  }
  
  public void mouseReleased(MouseEvent e)
  {
  }
  
  public void mousePressed(MouseEvent e)
  {
  }
  
  public void mouseClicked(MouseEvent e)
  {
    //none selected previously
    int col = e.getX() / (SPACING + CARD_WIDTH);
    int row = e.getY() / (SPACING + CARD_HEIGHT);
    if (row > 1)
      row = 1;
    if (col > 6)
      col = 6;
    
    if (row == 0 && col == 0)
      game.stockClicked();
    else if (row == 0 && col == 1)
      game.wasteClicked();
    else if (row == 0 && col >= 3)
      game.foundationClicked(col - 3);
    else if (row == 1)
      game.pileClicked(col);
    repaint();
  }
  
  private void drawBorder(Graphics g, int x, int y)
  {
    g.setColor(Color.YELLOW);
    g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
    g.drawRect(x + 1, y + 1, CARD_WIDTH - 2, CARD_HEIGHT - 2);
    g.drawRect(x + 2, y + 2, CARD_WIDTH - 4, CARD_HEIGHT - 4);
  }
  
  public void unselect()
  {
    selectedRow = -1;
    selectedCol = -1;
  }
  
  public boolean isWasteSelected()
  {
    return selectedRow == 0 && selectedCol == 1;
  }
  
  public void selectWaste()
  {
    selectedRow = 0;
    selectedCol = 1;
  }
  
  public boolean isPileSelected()
  {
    return selectedRow == 1;
  }
  
  public int selectedPile()
  {
    if (selectedRow == 1)
      return selectedCol;
    else
      return -1;
  }
  
  public void selectPile(int index)
  {
    selectedRow = 1;
    selectedCol = index;
  }
}