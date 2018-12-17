package antsimulator;

public class AdjacentSquares

{
	private Location north;
	private Location northEast;
	private Location east;
	private Location southEast;
	private Location south;
	private Location southWest;
	private Location west;
	private Location northWest;

	public AdjacentSquares()
	{

	}

	public AdjacentSquares(int currentX, int currentY)
	{
		this.setNorth(currentX, currentY);
		this.setNorthEast(currentX, currentY);
		this.setEast(currentX, currentY);
		this.setSouthEast(currentX, currentY);
		this.setSouth(currentX, currentY);
		this.setSouthWest(currentX, currentY);
		this.setWest(currentX, currentY);
		this.setNorthWest(currentX, currentY);
	}

	public void setNorth(int currentX, int currentY)
	{
		north = new Location(currentX, currentY - 1);
		north.setDirection("north");
	}

	public Location getNorth()
	{
		return north;
	}

	public void setNorthEast(int currentX, int currentY)
	{
		northEast = new Location(currentX + 1, currentY - 1);
		northEast.setDirection("northeast");
	}

	public Location getNorthEast()
	{
		return northEast;
	}

	public void setEast(int currentX, int currentY)
	{
		east = new Location(currentX + 1, currentY);
		east.setDirection("east");
	}

	public Location getEast()
	{
		return east;
	}

	public void setSouthEast(int currentX, int currentY)
	{
		southEast = new Location(currentX + 1, currentY + 1);
		southEast.setDirection("southeast");
	}

	public Location getSouthEast()
	{
		return southEast;
	}

	public void setSouth(int currentX, int currentY)
	{
		south = new Location(currentX, currentY + 1);
		south.setDirection("south");
	}

	public Location getSouth()
	{
		return south;
	}

	public void setSouthWest(int currentX, int currentY)
	{
		southWest = new Location(currentX - 1, currentY + 1);
		southWest.setDirection("southwest");
	}

	public Location getSouthWest()
	{
		return southWest;
	}

	public void setWest(int currentX, int currentY)
	{
		west = new Location(currentX - 1, currentY);
		west.setDirection("west");
	}

	public Location getWest()
	{
		return west;
	}

	public void setNorthWest(int currentX, int currentY)
	{
		northWest = new Location(currentX - 1, currentY - 1);
		northWest.setDirection("northwest");
	}

	public Location getNorthWest()
	{
		return northWest;
	}
}
