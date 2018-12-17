package antsimulator;

public class Forager extends WorkerAnt
{
	//Variables -----------------------------------------------------------------------------------------------------------------------------------------------
	private boolean carryingFood = false;
	private ArrayStack movementHistory = new ArrayStack();
	private Location loc;
	private Location newLoc;
	private int highestPh = 0;
	private ArrayList locations = new ArrayList();


	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	public Forager()
	{
		super();
	}

	public Forager(int locX, int locY)
	{
		super(locX, locY);
		super.setAntType("Forager");
	}

	//Methods ------------------------------------------------------------------------------------------------------------------------------------------------
	public void setCarryingFood(boolean carryingFood)
	{
		this.carryingFood = carryingFood;
	}

	public void pickUpFood()
	{
		this.carryingFood = true;
	}

	public void depositFood()
	{
		this.carryingFood = false;
	}

	public boolean getCarryingFood()
	{
		return carryingFood;
	}

	public void move(int locX, int locY, Environment[][] board)
	{
		this.setLocation(locX, locY);
		board[locX][locY].addForagers();
	}


	//Save movement history as a location object and add to stack
	public void createLocObj(int x, int y)
	{
		loc = new Location(x, y);
		movementHistory.push(loc);
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

	public void returnToNest(Environment[][] board)
	{
		if (!movementHistory.isEmpty())
		{
			dropPheromones(board, this.getLocX(), this.getLocY());
			moveToRecalledHistory(board, this.getLocX(), this.getLocY());
			depositFoodForQueen(board, this.getLocX(), this.getLocY());
		}
	}

	public void dropPheromones(Environment[][] board, int x, int y)
	{
		if (board[x][y].getQueenPresent() == false)
		{
			board[x][y].increasePheromones();
		}
	}

	public void moveToRecalledHistory(Environment[][] board, int x, int y)
	{
		newLoc = (Location)(movementHistory.peek());
		int newX = newLoc.getLocX();
		int newY = newLoc.getLocY();

		board[x][y].removeForagers();

		this.setLocation(newX, newY);
		board[newX][newY].addForagers();

		movementHistory.pop();
	}

	public void depositFoodForQueen(Environment[][] board, int x, int y)
	{
		if (board[x][y].getQueenPresent())
		{
			this.depositFood();
			board[x][x].increaseNumOfFood();
			movementHistory.clear();
		}
	}

	public ArrayStack getMovementHistory()
	{
		return movementHistory;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------
	public void forage(Environment[][] board)
	{
		int maxNumOfAdjacentSquares = 8;
		int hxSize;

		createAdjacentSquares(this.getLocX(), this.getLocY());
		validateSurroundingSquares(board);
		checkAdjacentVisibility(board);

		//Check adjacent squares to see if forager has recently been there
		if(!movementHistory.isEmpty())
		{
			hxSize = movementHistory.size();

			if (hxSize <= maxNumOfAdjacentSquares)
			{
				removePreviousFromPosibility(movementHistory.size());
			}
			else
			{
				removePreviousFromPosibility(maxNumOfAdjacentSquares);
			}
		}

		checkAdjacentPheromones(board);
		createLocObj(this.getLocX(), this.getLocY());

		addAdjacentSquaresToLocations(board);
		setMoveTo(chooseNewLocation(locations));
		moveToNewLocation(board);

		return;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------

	public void removePreviousFromPosibility(int size)
	{
		while (size != 0)
		{
			int adjacentX;
			int adjacentY;

			Location previousLocation = (Location)(movementHistory.peek());
			int previousX = previousLocation.getLocX();
			int previousY = previousLocation.getLocY();

			if (getAdjacentSquares().size() > 1)
			{
				for (int k = 0; k < getAdjacentSquares().size(); k++)
				{
					adjacentX = ((Location)getAdjacentSquares().get(k)).getLocX();
					adjacentY = ((Location)getAdjacentSquares().get(k)).getLocY();

					if (previousX == adjacentX && previousY == adjacentY)
					{
						getAdjacentSquares().remove(k);
					}
				}
			}
			movementHistory.pop();
			size--;
			removePreviousFromPosibility(size);
			movementHistory.push(previousLocation);
		}
	}


	public void checkAdjacentPheromones(Environment[][] board)
	{
		setHighestPh(0);
		int adjacentPh;
		int adjacentX;
		int adjacentY;

		for (int j = 0; j < getAdjacentSquares().size(); j++)
		{
			adjacentX = ((Location)getAdjacentSquares().get(j)).getLocX();
			adjacentY = ((Location)getAdjacentSquares().get(j)).getLocY();
			adjacentPh = board[adjacentX][adjacentY].getNumOfPher();

			if (adjacentPh > getHighestPh())
			{
				setHighestPh(adjacentPh);
			}
		}
	}


	public void addAdjacentSquaresToLocations(Environment[][] board)
	{
		locations.clear();
		int adjacentPh;
		int adjacentX;
		int adjacentY;

		for (int j = 0; j < getAdjacentSquares().size(); j++)
		{
			adjacentX = ((Location)getAdjacentSquares().get(j)).getLocX();
			adjacentY = ((Location)getAdjacentSquares().get(j)).getLocY();
			adjacentPh = board[adjacentX][adjacentY].getNumOfPher();

			if (adjacentPh == highestPh)
			{
				setMoveTo((Location)(getAdjacentSquares().get(j)));
				locations.add(getMoveTo());
			}
		}
	}

	public void moveToNewLocation(Environment[][] board)
	{
		int newX = getMoveTo().getLocX();
		int newY = getMoveTo().getLocY();

		board[this.getLocX()][this.getLocY()].removeForagers();
		move(newX, newY, board);

		if (board[newX][newY].getNumOfFood() > 0 && board[newX][newY].getQueenPresent() == false)
		{
			this.pickUpFood();
			board[newX][newY].decreaseNumOfFood();
			return;
		}
	}


	public void setHighestPh(int highestPh)
	{
		this.highestPh = highestPh;
	}


	public int getHighestPh()
	{
		return highestPh;
	}


	public String toString()
	{
		String description = super.toString() + "\nCarrying Food: " + getCarryingFood();
		return description;
	}


	public void removeFromEnvironment(Environment board[][])
	{
		int x = this.getLocX();
		int y = this.getLocY();

		this.depositFood();
		board[x][y].increaseNumOfFood();
		board[x][y].removeForagers();
	}
}
