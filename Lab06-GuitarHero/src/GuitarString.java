import java.util.Random;

public class GuitarString 
{

    private static double SAMPLING_RATE = 44100;
    private static double ENERGY_DECAY_FACTOR = 0.994;
    private static Random random = new Random();
    private static boolean IS_DEBUG = false;

    private RingBuffer buffer; // ring buffer
    private int time;
    
    // YOUR OTHER INSTANCE VARIABLES HERE

    /** create a guitar string of the given frequency */
    public GuitarString(double frequency) {
        buffer = new RingBuffer((int)Math.round(SAMPLING_RATE / frequency));
    }

    /** create a guitar string with size & initial values given by the array */
    public GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);
        for(int i = 0 ; i < init.length ; i++) buffer.enqueue(init[i]);
    }

    /** create a guitar string based off the index of the note */
    public GuitarString(int index) {
        this(27.5 * Math.pow(1.05946, index));
    }

    /** pluck the guitar string by replacing the buffer with white noise */
    public void pluck() {
        RingBuffer b = new RingBuffer(buffer.capacity());
        for(int i = 0 ; i < buffer.capacity() ; i++) {
            b.enqueue(Math.random() + -0.5);
        }
        buffer = b;
    }

    /** advance the simulation one time step */
    public void tic() {
        // Program wouldn't run when the buffer was empty.
        if(buffer.isEmpty()) return;
        double dequeue = buffer.dequeue();
        double sample = sample();
        buffer.enqueue(((dequeue + sample) / 2) * ENERGY_DECAY_FACTOR);
        time++;
    }

    /** return the current sample */
    public double sample() {
        return buffer.peek();
    }

    /** return number of times tic was called */
    public int time() {
        return time;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public static void main(String[] args) {
        int N = 25;
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
            if(IS_DEBUG) System.out.println(testString.toString());
        }
        /*
         * Your program should produce the following output when the main() 
         * method runs (DON'T WORRY ABOUT VERY MINOR DIFFERENCES, E.G. OFF BY 0.001):
                0   0.2000
			    1   0.4000
			    2   0.5000
			    3   0.3000
			    4  -0.2000
			    5   0.4000
			    6   0.3000
			    7   0.0000
			    8  -0.1000
			    9  -0.3000
			   10   0.2988
			   11   0.4482
			   12   0.3984
			   13   0.0498
			   14   0.0996
			   15   0.3486
			   16   0.1494
			   17  -0.0498
			   18  -0.1992
			   19  -0.0006
			   20   0.3720
			   21   0.4216
			   22   0.2232
			   23   0.0744
			   24   0.2232
         */
    }
}
