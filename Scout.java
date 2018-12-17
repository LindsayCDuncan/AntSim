package antsimulator;

public class Scout extends WorkerAnt
{


	//Constructors --------------------------------------------------------------------------------------------------------------------------------------------
	public Scout()
	{
		super();
	}

	public Scout(int locX, int locY)
	{
		super(locX, locY);
		super.setAntType("Scout");
	}


	public void scouting(Environment[][] board)
	{
		createAdjacentSquares(this.getLocX(), this.getLocY());
		validateSurroundingSquares(board);
		setMoveTo(chooseNewLocation(getAdjacentSquares()));
		moveToNewLocation(board);
		revealBoardSquare(board);
	}


	public void move(int locX, int locY, Environment[][] board)
	{
		this.setLocation(locX, locY);
		board[locX][locY].addScouts();
	}

	public void moveToNewLocation(Environment[][] board)
	{
		int newX = getMoveTo().getLocX();
		int newY = getMoveTo().getLocY();

		board[this.getLocX()][this.getLocY()].removeScouts();
		move(newX, newY, board);
	}

	public void revealBoardSquare(Environment[][] board)
	{
		int currentX = this.getLocX();
		int currentY = this.getLocY();

		if (board[currentX][currentY].getIsRevealed() != true)
		{
			board[currentX][currentY].setRevealed(true);
			board[currentX][currentY].getSquare().showNode();
		}
	}
}
