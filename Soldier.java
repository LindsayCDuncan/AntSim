package antsimulator;

public class Soldier extends WorkerAnt implements Attackable
{
	private boolean enemyNear = false;
	private int attack = 0;
	private ArrayList enemyLocations = new ArrayList();


	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	public Soldier()
	{
		super();
	}

	public Soldier(int locX, int locY)
	{
		super(locX, locY);
		super.setAntType("Soldier");
	}

	//Attackable interface implementation --------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean detectEnemy(Environment[][] board)
	{
		int x = this.getLocX();
		int y = this.getLocY();

		if (board[x][y].getNumOfBalas() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getAttackChance()
	{
		this.attack = GameBoard.getRandNum().nextInt(2);
		return attack;
	}

	public void killEnemy(Environment[][] board, ArrayList ants)
	{
		int x = this.getLocX();
		int y = this.getLocY();

		int indexToKill = findEnemyInColony(ants);

		((Ant)ants.get(indexToKill)).setDeath();
		ants.remove(indexToKill);
		board[x][y].removeBalas();
	}

	public int findEnemyInColony(ArrayList ants)
	{
		int index = -1;
		int x = this.getLocX();
		int y = this.getLocY();
		boolean killThisAnt = false;

		for (int i = 0; i < ants.size(); i++)
		{
			if (ants.get(i) instanceof Bala)
			{
				killThisAnt = compareLocations((Bala)ants.get(i), x, y);
			}

			if (killThisAnt)
			{
				index = i;
				break;
			}
		}
		return index;
	}

	public boolean compareLocations(Bala bala, int soldierX, int soldierY)
	{
		if (bala.getLocX() == soldierX && bala.getLocY() == soldierY)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	@Override
	public boolean attackEnemy(int attackChance)
	{
		if (attackChance == 1)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	public void scoutForEnemy(Environment[][] board)
	{
		createAdjacentSquares(this.getLocX(), this.getLocY());
		validateSurroundingSquares(board);
		checkAdjacentVisibility(board);
		checkAdjacentForEnemy(board);

		if (!enemyLocations.isEmpty())
		{
			setMoveTo(chooseNewLocation(enemyLocations));
		}
		else
		{
			setMoveTo(chooseNewLocation(getAdjacentSquares()));
		}

		moveToNewLocation(board);
	}


	public void checkAdjacentForEnemy(Environment[][] board)
	{
		enemyLocations.clear();

		Location adjacent;
		int adjacentX;
		int adjacentY;
		int numOfBalas;

		if (!getAdjacentSquares().isEmpty())
		{
			for (int j = 0; j < getAdjacentSquares().size(); j++)
			{
				adjacent = (Location)(getAdjacentSquares().get(j));
				adjacentX = adjacent.getLocX();
				adjacentY = adjacent.getLocY();
				numOfBalas = board[adjacentX][adjacentY].getNumOfBalas();

				if (numOfBalas > 0)
				{
					enemyLocations.add(adjacent);
				}
			}
		}
	}

	public void move(int locX, int locY, Environment[][] board)
	{
		this.setLocation(locX, locY);
		board[locX][locY].addSoldiers();
	}

	public void moveToNewLocation(Environment[][] board)
	{
		int newX = getMoveTo().getLocX();
		int newY = getMoveTo().getLocY();

		board[this.getLocX()][this.getLocY()].removeSoldiers();
		move(newX, newY, board);
	}


	public void setEnemyNear()
	{
		this.enemyNear = true;
	}

	public boolean isEnemyNear()
	{
		return enemyNear;
	}


	public void removeFromBoard(Environment[][] board)
	{
		int x = this.getLocX();
		int y = this.getLocY();

		board[x][y].removeSoldiers();
	}
}
