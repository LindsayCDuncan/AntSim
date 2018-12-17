package antsimulator;

public class Colony
{
	//Variables -----------------------------------------------------------------------------------------------------------------------------------------------
	private Environment[][] board = new Environment[27][27];
	private ArrayList antColony = new ArrayList();
	private ColonyView view = new ColonyView(27, 27);


	//Constructor ----------------------------------------------------------------------------------------------------------------------------------------------
	public Colony()
	{

	}

	public ColonyView getColonyView()
	{
		return view;
	}

	public void setColony()
	{
		fillInitialAntColony(antColony);
		fillEnvironment(board);
		setNodes(view);
		setAnts(antColony, board);

		//Reveal Queen's node
		board[13][13].getSquare().showNode();
		ArrayList revealAdjacent = new ArrayList();
		revealAdjacentNodes(13, 13, revealAdjacent);
	}

	public void fillEnvironment(Environment[][] board)
	{
		int chance = 0;
		int food = 0;
		boolean queenPresent;
		boolean visible;

		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board[row].length; column++)
			{
				if (row == 13 && column == 13)
				{
					food = 1000;
					queenPresent = true;
					visible = true;
				}

				else
				{
					chance = GameBoard.getRandNum().nextInt(4) + 1;

					switch(chance)
					{
						case 1: food = GameBoard.getRandNum().nextInt(1000 - 500 + 1) + 500; break;
						case 2: food = 0; break;
						case 3: food = 0; break;
						case 4: food = 0; break;
						default: break;
					}

					queenPresent = false;
					visible = false;
				}
				board[row][column] = new Environment(row, column, visible, queenPresent, food);
			}
		}
	}

	//Fill ant colony with initial ants
	public void fillInitialAntColony(ArrayList antColony)
	{
		//Add 1 queen
		addQueen(antColony, 13, 13);

		//Add 4 scouts
		for (int i = 0; i < 4; i++)
		{
			addScout(antColony, 13, 13);
		}

		//Add 10 soldiers
		for (int i = 0; i < 10; i++)
		{
			addSoldier(antColony, 13, 13);
		}

		//Add 50 foragers
		for (int i = 0; i < 50; i++)
		{
			addForager(antColony, 13, 13);
		}
	}

	//Add queen to colony
	public void addQueen(ArrayList antColony, int x, int y)
	{
		Ant queen = new Queen(x, y);
		antColony.add(queen);
	}

	//Add new scout to colony
	public void addScout(ArrayList antColony, int x, int y)
	{
		Ant scout = new Scout(x, y);
		antColony.add(scout);
	}

	//Add new soldier to colony
	public void addSoldier(ArrayList antColony, int x, int y)
	{
		Ant soldier = new Soldier(x, y);
		antColony.add(soldier);
	}

	//Add new forager to colony
	public void addForager(ArrayList antColony, int x, int y)
	{
		Ant forager = new Forager(x, y);
		antColony.add(forager);
	}

	//Add new Bala
	public void addBala(ArrayList antColony, int x, int y)
	{
		Ant bala = new Bala(x, y);
		antColony.add(bala);
	}

	public ArrayList seeColony()
	{
		return antColony;
	}

	public Environment[][] seeBoard()
	{
		return board;
	}

	//Set initial view nodes
	public void setNodes(ColonyView view)
	{
		for (int x = 0; x < board.length; x++)
		{
			for (int y = 0; y < board[x].length; y++)
			{
				updateBoard();

				//Set square ID
				board[x][y].getSquare().setID(x + ", " + y);

				//Set queen presence
				if (board[x][y].getQueenPresent() == true)
				{
					board[x][y].getSquare().setQueen(true);
				}
				else
				{
					board[x][y].getSquare().setQueen(false);
				}

				//Add nodes to colony view
				view.addColonyNodeView(board[x][y].getSquare(), x, y);
			}
		}
	}

	//Set initial ant quantities
	public void setAnts(ArrayList antColony, Environment[][] board)
	{
		board[13][13].setNumOfAnts(antColony);
		updateBoard();
	}


	public void updateBoard()
	{
		for (int x = 0; x < board.length; x++)
		{
			for (int y = 0; y < board[x].length; y++)
			{
				board[x][y].getSquare().setFoodAmount(board[x][y].getNumOfFood());
				board[x][y].getSquare().setPheromoneLevel(board[x][y].getNumOfPher());
				board[x][y].getSquare().setForagerCount(board[x][y].getNumOfForagers());
				board[x][y].getSquare().setSoldierCount(board[x][y].getNumOfSoldiers());
				board[x][y].getSquare().setScoutCount(board[x][y].getNumOfScouts());
				board[x][y].getSquare().setBalaCount(board[x][y].getNumOfBalas());

				//Show scouts if present
				if (board[x][y].getNumOfScouts() > 0)
				{
					board[x][y].getSquare().showScoutIcon();
				}

				else
				{
					board[x][y].getSquare().hideScoutIcon();
				}

				//Show soldiers if present
				if (board[x][y].getNumOfSoldiers() > 0)
				{
					board[x][y].getSquare().showSoldierIcon();
				}

				else
				{
					board[x][y].getSquare().hideSoldierIcon();
				}

				//Show foragers if present
				if (board[x][y].getNumOfForagers() > 0)
				{
					board[x][y].getSquare().showForagerIcon();
				}

				else
				{
					board[x][y].getSquare().hideForagerIcon();
				}

				//Show balas if present
				if (board[x][y].getNumOfBalas() > 0)
				{
					board[x][y].getSquare().showBalaIcon();
				}

				else
				{
					board[x][y].getSquare().hideBalaIcon();
				}

				//Show queen if present
				if (board[x][y].getQueenPresent())
				{
					board[x][y].getSquare().showQueenIcon();
				}

				else
				{
					board[x][y].getSquare().hideQueenIcon();
				}
			}
		}
	}

	public void revealAdjacentNodes(int x, int y, ArrayList reveal)
	{
		AdjacentSquares surroundings = new AdjacentSquares(x, y);

		Location location;
		int locX;
		int locY;

		reveal.add(surroundings.getNorth());
		reveal.add(surroundings.getNorthEast());
		reveal.add(surroundings.getEast());
		reveal.add(surroundings.getSouthEast());
		reveal.add(surroundings.getSouth());
		reveal.add(surroundings.getSouthWest());
		reveal.add(surroundings.getWest());
		reveal.add(surroundings.getNorthWest());

		for (int k = 0; k < reveal.size(); k++)
		{
			location = (Location)(reveal.get(k));
			locX = location.getLocX();
			locY = location.getLocY();

			board[locX][locY].setRevealed(true);
			board[locX][locY].getSquare().showNode();
		}
	}
}
