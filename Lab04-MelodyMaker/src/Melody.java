import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class Melody {

  

  private static boolean WithRepeat = true;

  
  private Queue<Note> notes;
  

  public Melody(Queue<Note> song) {
    notes = song;
  }

  public double getTotalDuration() {
    return 0;
  }

  public String toString() {
    String s = "";
    Queue<Note> n = new LinkedList<>();
    while(notes.peek() != null) {
      n.offer(notes.peek());
      s += notes.poll().toString();
    }
    notes = n;
    return s;
  }

  public void changeTempo(double tempo) {
    Queue<Note> n = new LinkedList<>();
    while(notes.peek() != null) {
      Note note = notes.poll();
      note.setDuration(note.getDuration() * tempo);
      n.offer(note);
    }
    notes = n;
  }

  public void reverse() {
    LinkedList<Note> n = new LinkedList<>(notes);
    notes.clear();
    Iterator<Note> i = n.descendingIterator();
    while(i.hasNext()) {
      notes.offer(i.next());
    }
  }

  public void append(Melody other) {
    Melody o = other.clone();
    o.reverse();
    while(o.getNotes().peek() != null) {
      notes.offer(o.getNotes().poll());
    }
  }

  
  public void play() {
    Queue<Note> o = new LinkedList<>(notes);
    Queue<Note> repeats = new LinkedList<>();
    if(WithRepeat) {
      while(notes.peek() != null) {
        Note n = notes.poll();
        n.play();
        repeats.offer(n);
        if(n.isRepeat()) {
          while(repeats.peek() != null) {
            repeats.poll().play();
          }
        }
      }
    } else {
      while(notes.peek() != null) {
        notes.poll().play();
      }
    }
    

    notes = o;
  }

  public Queue<Note> getNotes() {
    return notes;
  }

  @Override
  protected Melody clone() {
    return new Melody(new LinkedList<>(notes));
  }
}