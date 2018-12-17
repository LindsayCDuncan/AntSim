package antsimulator;


public class Queen extends Ant
{

	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	public Queen()
	{
		super();
	}

	public Queen(int locX, int locY)
	{
		super(locX, locY);
		super.setAntType("Queen");
		super.setLifeSpan(73000);
	}

	//Methods ------------------------------------------------------------------------------------------------------------------------------------------------

	public void hatchNewAnt(ArrayList ants, Environment[][] board, Colony colony)
	{
		int chance = GameBoard.getRandNum().nextInt(4) + 1;

		switch(chance)
		{
			case 1: colony.addScout(ants, 13, 13); board[13][13].addScouts(); break;
			case 2: colony.addSoldier(ants, 13, 13); board[13][13].addSoldiers(); break;
			case 3: colony.addForager(ants, 13, 13); board[13][13].addForagers(); break;
			case 4: colony.addForager(ants, 13, 13); board[13][13].addForagers(); break;
			default: break;

		}

	}

}
