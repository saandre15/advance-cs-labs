import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class GuitarHero {

    private static int START_INDEX = 40;
    
    public static void main(String[] args) {
        
        HashMap<Character, GuitarString> notes = new HashMap<>(37);
        int counter = 0;

        try(Scanner s = new Scanner(new File("keyboard.txt"))) {
            while(s.hasNext()) {
                notes.put(s.next().charAt(0), new GuitarString(START_INDEX + counter));
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Invalid GUI FILE");
            System.exit(-1);
        }
        StdDraw.setCanvasSize(1864, 260);
        StdDraw.picture(0.5, 0.5, "keyboard.png");

        while(true) {
            if(StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                GuitarString note = notes.get(key);
                if(note == null) continue;
                note.pluck();
            }
            double sample = notes
                .values()
                .stream()
                .map(cur -> cur.sample())
                .reduce((prev, cur) -> prev + cur)
                .orElseThrow(RuntimeException::new);
            // System.out.println(sample);
            
            StdAudio.play(sample);
            
            notes.values()
                .stream()
                .forEach(note -> note.tic());
        }

    }

}
