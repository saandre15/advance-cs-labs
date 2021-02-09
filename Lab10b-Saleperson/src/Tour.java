import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Tour 
{
	private Node head;
	private int size;
	private double distance = -1;

	private class Node implements Cloneable {
		Point p;
		Node next;
		
		Node(Point p) {
			this(p, null);
		}

		Node(Point p, Node next) {
			this.p = p;
			this.next = next;
		}

		Node withNext(Node next) {
			return new Node(p, next);
		}

		public Node getNext() {
			return next;
		}

		public Point getPoint() {
			return p;
		}

	}

	/** create an empty tour */
	public Tour()
	{
		this.head = null;
		this.size = 0;
	}
	
	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d)
	{
		add(a); add(b); add(c); add(d);
	}

	private void add(Point p) 
	{
		if(this.head == null) {
			head = new Node(p);
			size++;
			return;
		}
		Node cur = this.head;
		while(cur.next != null) {
			cur = cur.next;
		}
		cur.next = new Node(p);
		size++;
	}
	
	/** print tour (one point per line) to std output */
	public void show()
	{
		String s = "";
		Node cur = this.head;
		int counter = 1;
		while(cur != null) {
			s +=  counter + ". " + cur.getPoint().toString() + "\n";
			cur = cur.next;
			counter++;
		}
		System.out.println(s);
	}
	
	/** draw the tour using StdDraw */
	public void draw()
	{
		Node cur = this.head;
		while(cur != null) {
			if(cur.next != null) cur.getPoint().drawTo(cur.next.getPoint());
			cur = cur.next;
		}
	}
	
	/** return number of nodes in the tour */
	public int size()
	{
		return size;
	}

	private void setDistance(double d) {
		this.distance = d;
	}
	
	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance()
	{

	
		double distance = 0;
		Node cur = this.head;
		while(cur != null) {
			if(cur.next != null) distance += cur.getPoint().distanceTo(cur.next.getPoint());
			cur = cur.next;
		}
		return distance;
	}

	
	/** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p) 
    {
		if(size == 0) {
			add(p);
			return;
		}
		Node cur = this.head;
		Node nearest = this.head;
		while(cur != null) {
			if(cur.getPoint().distanceTo(p) < nearest.getPoint().distanceTo(p)) nearest = cur;
			cur = cur.next;
		}
		Node insert = new Node(p, nearest.next);
		nearest.next = insert;
	}

	private void insertAt(Point p, int index) {
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		if(this.size == 0) {
			add(p);
			return;
		}
		Node cur = this.head;
		int counter = 0;
		while(cur != null) {
			if(counter == index) {
				Node insert = new Node(p, cur.next);
				cur.next = insert;
				size++;
				return;
			}
			cur = cur.next;
			counter++;
		}
	}

	// Inferior Implementation - Compares the integral of the entire function of point with respect to distance.
	// public void insertSmallest(Point p) {
	// 	if(this.size == 0) {
	// 		add(p);
	// 		return;
	// 	}
		// Node cur = this.head;
		// double minDist = Double.MAX_VALUE;
		// int minIndex = -1;
		// int counter = 0;
		// while(cur != null) {
		// 	Node next = cur.next;

		// 	Node insert = new Node(p, cur.next);
		// 	cur.next = insert;

		// 	double distance = distance();

		// 	if(distance < minDist) {
		// 		minDist = distance;
		// 		minIndex = counter; 
		// 	}

		// 	cur.next = next;
			
		// 	if(cur.next == null) System.out.println("Counter " + counter);

		// 	cur = cur.next;
		// 	counter++;
		// 	// Slows down
		// 	// show();
		// }
		// insertAt(p, minIndex);
	// }



	/** insert p using smallest increase heuristic */
    public void insertSmallest(Point p) 
    {
		if(this.size == 0) {
			add(p);
			return;
		}
		// calculus - compares the smallest delta of the indefinite integral of a 
		// function of points with respect to distance where changes has occured.
		// minutes/hour computation -> seconds
		if(this.size == 1) {
			add(p);
			return;
		}

		Node cur = this.head;
		double dist = distance();
		double deltaMinDist = Double.MAX_VALUE;
		int minIndex = -1;
		int counter = 0;
		while(cur.next != null) {
			Node next = cur.next;
			Node insert = new Node(p, cur.next);
			cur.next = insert;


			double deltaDist = Math.abs((cur.getPoint().distanceTo(cur.next.getPoint()) 
			+ cur.next.getPoint().distanceTo(cur.next.next.getPoint())) 
			- cur.getPoint().distanceTo(cur.next.next.getPoint()));
			

			if(deltaDist < deltaMinDist) {
				deltaMinDist = deltaDist;
				minIndex = counter;
			}

			cur.next = next;


			if(cur.next == null) System.out.println("Counter " + counter);

			cur = cur.next;
			counter++;

		}
		insertAt(p, minIndex);
		
	}

	public static void main(String[] args) {
		Tour tour = new Tour(new Point(0, 1), new Point(1, 2), new Point(2, 3), new Point(3, 4));
		tour.show();

		System.out.println("Total size " + tour.size() );

		tour.insertAt(new Point(0.5, 0.5), 1);
		System.out.println("Insert at index = 1");
		tour.show();

		System.out.println("Total distance " + tour.distance());
		System.out.println("Total size " + tour.size() );

		// Last Index
		tour.insertNearest(new Point(5,5));
		tour.show();

		tour.insertSmallest(new Point(6, 6));
		tour.show();
	}

}