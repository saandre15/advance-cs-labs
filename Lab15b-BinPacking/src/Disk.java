import java.util.ArrayList;

import javax.lang.model.element.ModuleElement.UsesDirective;

class Disk implements Comparable<Disk> {

    // Size in terms of GB
    private static int SIZE = 1;

    public static int SIZE() {
        return Disk.SIZE;
    }

    int usedSpace;
    int availableSpace;
    private ArrayList<Integer> storage;
    private String uuid;

    public Disk(String uuid) {
        this.uuid = uuid;
        this.storage = new ArrayList<>();
        this.usedSpace = 0;
        this.availableSpace = SIZE * 1000000;
    }

    public boolean add(int filesize) {
        if(availableSpace - filesize < 0) return false;
        availableSpace -= filesize;
        usedSpace += filesize;
        return this.storage.add(filesize);
    }

    public boolean remove(int filesize) {
        if(!this.remove(filesize)) return false;
        availableSpace += filesize;
        usedSpace -= filesize;
        return true;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }

    public int getUsedSpace() {
        return usedSpace;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public int compareTo(Disk other) {
        int otherSize = other.getAvailableSpace();
        int thisSize = getAvailableSpace();
        if(otherSize > thisSize) return 1;
        else if(otherSize < thisSize) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return getUuid() + ": " + storage.toString();
    }

}