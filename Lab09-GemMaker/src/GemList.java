import java.util.stream.IntStream;

import java.awt.Point;

public class GemList extends LinkedList<Gem> 
{

	private static Point P_RESOLUTION = new Point(32, 34);

	public void draw(double y) {
		IntStream.range(0, size())
			.peek(index -> System.out.println(index))
			.forEach(index -> indexOf(index).draw((P_RESOLUTION.getX() * index) / 512, y));
	}

	@Override
	public String toString() {
		if(size() == 0) return "<none>";
		return  IntStream
			.range(0, size())
			.mapToObj(index -> indexOf(index))
			.map(cur -> cur.toString().toUpperCase())
			.reduce((prev, cur) -> prev + " -> " + cur)
			.orElseThrow(RuntimeException::new);
	}

	public void insertBefore(Gem gem, int index) {
		if(index >= size()) {
			add(gem);
			return;
		}
		add(gem, index);
	}

	public int score() {
		if(size() == 0) return 0;
		return IntStream.range(0, size())
			.map(index -> indexOf(index).getPoints() 
			* (int) (IntStream.range(0, size())
				.mapToObj(i -> indexOf(i))
				.filter(cur -> cur.getType() == indexOf(index).getType()))
				.count())
			.reduce((prev, cur) -> prev + cur)
			.orElseThrow(RuntimeException::new);
	}

	

	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}	
}
