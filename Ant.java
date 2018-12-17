package antsimulator;

public abstract class Ant
{
	//Variables -----------------------------------------------------------------------------------------------------------------------------------------------
	private String antType = "Ant";
	private static int accumAntID = 0;
	private int antID = 0;
	private int lifeSpan = 3650;
	private int age = 0;
	private int locX = 13;
	private int locY = 13;
	private boolean isAlive = true;

	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	protected Ant()
	{

	}

	protected Ant (int locX, int locY)
	{
		this.antID = accumAntID;
		accumAntID++;
		this.locX = locX;
		this.locY = locY;
	}


	//Methods ------------------------------------------------------------------------------------------------------------------------------------------------

	public int getAntID()
	{
		return antID;
	}

	public void setLocation(int locX, int locY)
	{
		this.locX = locX;
		this.locY = locY;
	}

	public int getLocX()
	{
		return locX;
	}

	public int getLocY()
	{
		return locY;
	}

	public void setLifeSpan(int lifeSpan)
	{
		this.lifeSpan = lifeSpan;
	}

	public int getLifeSpan()
	{
		return lifeSpan;
	}

	public void incrementAge()
	{
		this.age += 1;
		if (this.age == this.lifeSpan)
		{
			setDeath();
		}
	}

	public int getAge()
	{
		return age;
	}

	public void setDeath()
	{
		this.isAlive = false;
	}

	public boolean getIsAlive()
	{
		return isAlive;
	}

	public void setAntType(String antType)
	{
		this.antType = antType;
	}

	public String getAntType()
	{
		return antType;
	}

	public String toString()
	{
		String description = "Ant Type: " + getAntType() + "\nAnt ID: " + getAntID() + " \nLife Span: " + getLifeSpan() + "\nCurrect Age: " +
	getAge() + "\nLoc X: " + getLocX() + "\nLoc Y:  " + getLocY() + "\nAlive: " + getIsAlive();

		return description;
	}
}
