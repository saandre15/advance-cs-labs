public class MinHeap {

  static final int DEFAULT_CAPACITY = 8;

  private Integer[] heap;
  private int size;

  public MinHeap() {
    this(DEFAULT_CAPACITY);
  }

  public MinHeap(int size) {
    this.heap = new Integer[size];
    this.size = 0;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int peekMinimum() {
    return heap[0];
  }

  public int getLeftChildIndex(int index) {
    return 2 * index + 1;
  }

  public int getRightChildIndex(int index) {
    return 2 * index + 2;
  }

  public boolean hasRightChildIndex(int index) {
    try {
      if(heap[getRightChildIndex(index)] == null) return false;
      return true;
    }
    catch(IndexOutOfBoundsException e) {
      return false;
    }
  }

  public boolean hasLeftChildIndex(int index) {
    try {
      if(heap[getLeftChildIndex(index)] == null) return false;
      return true;
    }
    catch(IndexOutOfBoundsException e) {
      return false;
    }
  }

  public int getParentIndex(int index) {
    return (index - 1) / 2;
  }

  public void doubleCapacity() {
    Integer[] array = new Integer[heap.length *2];
    for(int i = 0 ; i < heap.length ; i++) array[i] = heap[i];
    heap = array;
  }

  public void insert(int val) {
    if(heap.length == size) doubleCapacity();
    heap[size] = val;
    size = size + 1;
    bubbleUp(size - 1);
  }

  private void bubbleUp(int index) {
    if(index == 0) return;
    int parentIndex = getParentIndex(index);
    int cur = heap[index];
    int parent = heap[parentIndex];
    if(cur < parent) {
      exchange(index, parentIndex);
      bubbleUp(parentIndex);
    }
  }

  private void exchange(int sourceIndex, int targetIndex) {
    heap[sourceIndex] += heap[targetIndex];
    heap[targetIndex] = heap[sourceIndex] - heap[targetIndex];
    heap[sourceIndex] = heap[sourceIndex] - heap[targetIndex];
  }

  public int popMinimum() {
    int min = heap[0];
    heap[0] = null;
    siftDown(0);
    return min;
  }

  private void siftDown(int index) {
    int rightIndex = -1;
    int leftIndex = -1;
    int smallestIndex = -1;
    if(hasRightChildIndex(index)) rightIndex = getRightChildIndex(index);
    if(hasLeftChildIndex(index)) leftIndex = getLeftChildIndex(index);
    if(rightIndex == -1 && leftIndex == -1) return;
    else if(rightIndex != -1 && leftIndex != -1) smallestIndex = heap[rightIndex] < heap[leftIndex] ? rightIndex : leftIndex;
    else if(rightIndex != -1) smallestIndex = rightIndex;
    else if (leftIndex != -1) smallestIndex = leftIndex;
    heap[index] = heap[smallestIndex];
    heap[smallestIndex] = null;
    siftDown(smallestIndex);
  }

  @Override
	public String toString()
	{
    
    String output = "";
    
    System.out.println(size);

		for (int i = 0; i <= getSize() - 1 ; i++) 
			output += heap[i] + ", ";

		return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
	}

	/** method borrowed with minor modifications from the internet somewhere, for printing */
	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 0;
		String dots = "...............................";
		System.out.println(dots + dots);      
		while (j < this.getSize())
		{
			if (column == 0)                 
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');

			System.out.print((heap[j] == null)? "" : heap[j]);
			
			if (++column == itemsPerRow) {
				nBlanks /= 2;                 
				itemsPerRow *= 2;             
				column = 0;                   
				System.out.println();         
			}
			else                             
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
			
			j++;
		}
		System.out.println("\n" + dots + dots); 
	}	


}