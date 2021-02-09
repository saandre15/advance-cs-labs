import java.awt.Font;
import java.io.File;


enum GemType {
	GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}
 
public class Gem
{

	private GemType type;
	private int points;


	public Gem() {
		randomize();
	}

	public Gem(GemType type, int points) {
		this.type = type;
		this.points = points;
	}

	@Override
	public String toString() {
		return type.toString() + " " + points;
	}

	public int getPoints() {
		return points;
	}

	public GemType getType() {
		return type;
	}

	public void draw(double x, double y) {
		StdDraw.picture(x, y, getFilename());
		StdDraw.text(x, y, String.valueOf(points));
	}

	private String getFilename() {
		return switch(this.type) {
			case BLUE -> "gem_blue.png";
			case GREEN -> "gem_green.png";
			case ORANGE -> "gem_orange.png";
		};
	}

	private void randomize() {
		points = ((int)(Math.random() * 10)) * 5;
		type = GemType.values()[((int)(Math.random() * 2))];
	}
	


	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
