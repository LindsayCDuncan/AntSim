package antsimulator;

public abstract class WorkerAnt extends Ant
{
	private Location moveTo;
	private ArrayList adjacentSquares = new ArrayList();

	protected WorkerAnt()
	{
		super();
	}

	protected WorkerAnt(int x, int y)
	{
		super(x, y);
	}

	public void createAdjacentSquares(int currentX, int currentY)
	{
		AdjacentSquares surroundingSquares = new AdjacentSquares(currentX, currentY);
		adjacentSquares.clear();

		adjacentSquares.add(surroundingSquares.getNorth());
		adjacentSquares.add(surroundingSquares.getNorthEast());
		adjacentSquares.add(surroundingSquares.getEast());
		adjacentSquares.add(surroundingSquares.getSouthEast());
		adjacentSquares.add(surroundingSquares.getSouth());
		adjacentSquares.add(surroundingSquares.getSouthWest());
		adjacentSquares.add(surroundingSquares.getWest());
		adjacentSquares.add(surroundingSquares.getNorthWest());
	}

	//Ensures potential movement locations are within the board dimensions
	public void validateSurroundingSquares(Environment[][] board)
	{
		int xMin = 0;
		int yMin = 0;
		int xMax = board.length - 1;
		int yMax = board[0].length - 1;

		Location adjacent;
		int checkX;
		int checkY;

		int index = 0;

		while(index < adjacentSquares.size())
		{
			adjacent = ((Location)adjacentSquares.get(index));
			checkX = adjacent.getLocX();
			checkY = adjacent.getLocY();

			if (checkX < xMin ||
				checkX > xMax ||
				checkY < yMin ||
				checkY > yMax)
			{
				adjacentSquares.remove(adjacent);
				continue;
			}
			else
			{
				index++;
			}
		}
	}

	public void checkAdjacentVisibility(Environment[][] board)
	{
		Location adjacent;
		int adjacentX;
		int adjacentY;
		boolean visible;

		int index = 0;

		while (index < adjacentSquares.size())
		{
			adjacent = ((Location)adjacentSquares.get(index));
			adjacentX = adjacent.getLocX();
			adjacentY = adjacent.getLocY();
			visible = board[adjacentX][adjacentY].getIsRevealed();

			if (!visible)
			{
				adjacentSquares.remove(adjacent);
				continue;
			}
			else
			{
				index++;
			}
		}
	}

	public Location chooseNewLocation(ArrayList locations)
	{
		int randLoc;
		int size = locations.size();

		if (size == 1)
		{
			return (Location)(locations.get(0));
		}

		else
		{
			randLoc = GameBoard.getRandNum().nextInt(size);
			return (Location)(locations.get(randLoc));
		}
	}

	public void moveToNewLocation(Environment[][] board)
	{
		int newX = moveTo.getLocX();
		int newY = moveTo.getLocY();

		move(newX, newY, board);
	}

	public void move(int locX, int locY, Environment[][] board)
	{
		this.setLocation(locX, locY);
	}

	public void setMoveTo(Location newLoc)
	{
		moveTo = newLoc;
	}

	public ArrayList getAdjacentSquares()
	{
		return adjacentSquares;
	}

	public Location getMoveTo()
	{
		return moveTo;
	}
}
