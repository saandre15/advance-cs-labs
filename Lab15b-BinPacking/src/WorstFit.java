import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WorstFit {

    private static String FILENAME ="input1000.txt";
    private static boolean DEBUG = true;

    public static void main(String[] args) {
        try(Scanner parser = new Scanner(new File(FILENAME))) { 
            PriorityQueue<Disk> diskQueue = new PriorityQueue<>();
            diskQueue.add(new Disk("" + ((int)(Math.random() * 1000000))));
            int fileNum = 0;
            while(parser.hasNextInt()) {
                int filesize = parser.nextInt();
                Disk disk = diskQueue.peek();
                int spaceLeft = disk.getAvailableSpace();
                if(filesize > spaceLeft) {
                    disk = new Disk("" + ((int)(Math.random() * 1000000)));
                    diskQueue.add(disk);
                    if(DEBUG) System.out.println("[DEBUG]: " + "A new " + Disk.SIZE() + " GB Disk has been created with a UUID of " +  disk.getUuid());
                }
                disk.add(filesize);
                fileNum++;
            }
            System.out.println("Total size = " + ((double)diskQueue.stream()
                .map(disk -> disk.getUsedSpace())
                .reduce((prev, cur) -> prev + cur)
                .orElseThrow(RuntimeException::new) / 1000000) + " GB");
            System.out.println("Wasted space size = " + ((double)diskQueue.stream()
                .map(disk -> disk.getAvailableSpace())
                .reduce((prev, cur) -> prev + cur)
                .orElseThrow(RuntimeException::new) / 1000000) + " GB");
            System.out.println("Disk req'd = " + diskQueue.size());
            if(fileNum < 100) diskQueue.stream().sorted(Disk::compareTo).forEach(disk -> System.out.println(disk));
        } catch(FileNotFoundException e) {
            System.out.println("Invalid filename passed into the client");
            System.exit(-1);
        }
    }

}
