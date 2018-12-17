package antsimulator;

public class Environment
{
	//Variables -----------------------------------------------------------------------------------------------------------------------------------------------
	private int locX;
	private int locY;
	private boolean isRevealed = false;
	private boolean queenPresent = false;
	private int numOfPher = 0;
	private int numOfFood = 0;
	private ColonyNodeView square;
	private int numOfScouts = 0;
	private int numOfSoldiers = 0;
	private int numOfForagers = 0;
	private int numOfBalas = 0;

	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	public Environment()
	{

	}

	public Environment(int locX, int locY, boolean revealed, boolean queenPresent, int numOfFood)
	{
		this.locX = locX;
		this.locY = locY;
		isRevealed = revealed;
		this.queenPresent = queenPresent;
		this.numOfFood = numOfFood;
		square = new ColonyNodeView();
	}

	//Methods ------------------------------------------------------------------------------------------------------------------------------------------------
	public void setNumOfAnts(ArrayList ants)
	{
		//Set initial num of ants

		for (int i = 0; i < ants.size(); i++)
		{
			if (ants.get(i) instanceof Ant)
			{
				if (this.getLocX() == (((Ant)ants.get(i)).getLocX()) && this.getLocY() == (((Ant)ants.get(i)).getLocY()))
				{
					if (ants.get(i) instanceof Scout)
					{
						addScouts();
					}

					else if (ants.get(i) instanceof Soldier)
					{
						addSoldiers();
					}

					else if (ants.get(i) instanceof Forager)
					{
						addForagers();
					}

					else if (ants.get(i) instanceof Bala)
					{
						addBalas();
					}

					else
					{
						continue;
					}
				}
			}
		}
	}

	public void addScouts()
	{
		numOfScouts++;
	}
	public void addForagers()
	{
		numOfForagers++;
	}
	public void addSoldiers()
	{
		numOfSoldiers++;
	}
	public void addBalas()
	{
		numOfBalas++;
	}

	public void removeScouts()
	{
		numOfScouts--;
	}
	public void removeForagers()
	{
		numOfForagers--;
	}
	public void removeSoldiers()
	{
		numOfSoldiers--;
	}
	public void removeBalas()
	{
		numOfBalas--;
	}

	public int getNumOfScouts()
	{
		return numOfScouts;
	}

	public int getNumOfForagers()
	{
		return numOfForagers;
	}

	public int getNumOfSoldiers()
	{
		return numOfSoldiers;
	}

	public int getNumOfBalas()
	{
		return numOfBalas;
	}

	public void setSquare(ColonyNodeView square)
	{
		this.square = square;
	}

	public ColonyNodeView getSquare()
	{
		return square;
	}


	public void setLocX(int locX)
	{
		this.locX = locX;
	}

	public void setLocY(int locY)
	{
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

	public void setRevealed(boolean revealed)
	{
		isRevealed = revealed;
	}

	public boolean getIsRevealed()
	{
		return isRevealed;
	}

	public void increasePheromones()
	{
		if (getNumOfPher() < 1000)
		{
			numOfPher += 10;
		}
	}

	public void decreasePheromones()
	{
		if (getNumOfPher() > 0)
		{
			numOfPher /= 2;
		}
	}

	public int getNumOfPher()
	{
		return numOfPher;
	}

	public void setNumOfFood(int food)
	{
		numOfFood = food;
	}

	public int getNumOfFood()
	{
		return numOfFood;
	}

	public void decreaseNumOfFood()
	{
		if (getNumOfFood() > 0)
		{
			numOfFood--;
		}
	}

	public void increaseNumOfFood()
	{
		numOfFood++;
	}


	public void setQueenPresentTrue()
	{
		queenPresent = true;
	}

	public boolean getQueenPresent()
	{
		return queenPresent;
	}

	public void removeQueen()
	{
		queenPresent = false;
	}

	public String toString()
	{
		String description = "Loc X: " + getLocX() + "\nLoc Y: " + getLocY() + "\nRevealed: " + getIsRevealed()  +
				"\nNumber of Pheromones: " + getNumOfPher() + "\nNumber of Food: " + getNumOfFood();
		return description;
	}



}
