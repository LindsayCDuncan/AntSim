package antsimulator;

public class Location
{
	private int locX;
	private int locY;
	private String direction;

	public Location ()
	{

	}

	public Location(int locX, int locY)
	{
		this.locX = locX;
		this.locY = locY;
		direction = "location";
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setLocX(int locX)
	{
		this.locX = locX;
	}

	public int getLocX()
	{
		return locX;
	}

	public void setLocY(int locY)
	{
		this.locY = locY;
	}

	public int getLocY()
	{
		return locY;
	}

}
