package antsimulator;

public class Bala extends WorkerAnt implements Attackable
{
	private int attack = 0;

	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	public Bala()
	{
		super();
	}

	public Bala(int locX, int locY)
	{
		super(locX, locY);
		super.setAntType("Bala");
	}

	//Attackable interface implementation --------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean detectEnemy(Environment [][] board)
	{
		int x = this.getLocX();
		int y = this.getLocY();

		if (board[x][y].getNumOfSoldiers() > 0 ||
			board[x][y].getNumOfForagers() > 0 ||
			board[x][y].getNumOfScouts() > 0 ||
			board[x][y].getQueenPresent())
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
		Ant antToKill = (Ant)(ants.get(indexToKill));

		if (ants.get(indexToKill) instanceof Soldier)
		{
			board[x][y].removeSoldiers();
		}

		else if (ants.get(indexToKill) instanceof Forager)
		{
			board[x][y].removeForagers();
		}

		else if (ants.get(indexToKill) instanceof Scout)
		{
			board[x][y].removeScouts();
		}

		else if (ants.get(indexToKill) instanceof Queen)
		{
			board[x][y].removeQueen();
		}

		antToKill.setDeath();

		if(!(antToKill instanceof Queen))
		{
			ants.remove(antToKill);
		}
	}

	public int findEnemyInColony(ArrayList ants)
	{
		int index = -1;
		int x = this.getLocX();
		int y = this.getLocY();
		boolean killThisAnt = false;

		for (int i = 0; i < ants.size(); i++)
		{
			if (ants.get(i) instanceof Soldier)
			{
				killThisAnt = compareLocations((Soldier)ants.get(i), x, y);
			}

			else if (ants.get(i) instanceof Forager)
			{
				killThisAnt = compareLocations((Forager)ants.get(i), x, y);
			}

			else if (ants.get(i) instanceof Scout)
			{
				killThisAnt = compareLocations((Scout)ants.get(i), x, y);
			}

			else if (ants.get(i) instanceof Queen)
			{
				killThisAnt = compareLocations((Queen)ants.get(i), x, y);
			}

			if (killThisAnt)
			{
				index = i;
				break;
			}
		}

		return index;
	}

	public boolean compareLocations(Ant ant, int balaX, int balaY)
	{
		if (ant.getLocX() == balaX && ant.getLocY() == balaY)
		{
			return true;
		}

		else
		{
			return false;
		}
	}


	public void moveAround(Environment[][] board)
	{
		createAdjacentSquares(this.getLocX(), this.getLocY());
		validateSurroundingSquares(board);
		setMoveTo(chooseNewLocation(getAdjacentSquares()));
		moveToNewLocation(board);
	}


	public void move(int locX, int locY, Environment[][] board)
	{
		this.setLocation(locX, locY);
		board[locX][locY].addBalas();
	}

	public void moveToNewLocation(Environment[][] board)
	{
		int newX = getMoveTo().getLocX();
		int newY = getMoveTo().getLocY();

		board[this.getLocX()][this.getLocY()].removeBalas();
		move(newX, newY, board);
	}


	public void removeFromBoard(Environment[][] board)
	{
		int x = this.getLocX();
		int y = this.getLocY();

		board[x][y].removeBalas();
	}
}
