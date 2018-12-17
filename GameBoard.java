package antsimulator;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class GameBoard implements SimulationEventListener, ActionListener
{
	//Variables -----------------------------------------------------------------------------------------------------------------------------------------------

	private int turnCount = 0;
	private int days = 0;
	private String time = "";
	private Colony antColony;
	private AntSimGUI newGame;
	private int balaChance = 0;
	private static Random randNum = new Random();
	private boolean continueSim = true;
	private javax.swing.Timer timerControl;

	public GameBoard()
	{

	}

	//Methods -------------------------------------------------------------------------------------------------------------------------------------------------

	public void initialize()
	{
		newGame = new AntSimGUI();
		antColony = new Colony();
		newGame.initGUI(getColony().getColonyView());
		newGame.addSimulationEventListener(this);
		setTime();
		newGame.setTime(getTime());
	}

	public void simulationEventOccurred(SimulationEvent simEvent)
	{
		if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT)
		{
			antColony.setColony();
		}

		else if (simEvent.getEventType() == SimulationEvent.QUEEN_TEST_EVENT)
		{

		}

		else if (simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT)
		{

		}

		else if (simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT)
		{

		}

		else if (simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT)
		{

		}

		else if (simEvent.getEventType() == SimulationEvent.RUN_EVENT)
		{
			start();
			timerControl.start();
		}

		else if (simEvent.getEventType() == SimulationEvent.STEP_EVENT)
		{
			if (continueSim)
			{
				takeTurns(antColony.seeColony(), antColony.seeBoard());
			}
		}

		else
		{

		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (continueSim)
		{
			takeTurns(antColony.seeColony(), antColony.seeBoard());
		}
		else
		{
			timerControl.stop();
		}
	}

	public void start()
	{
		timerControl = new Timer(1000, this);
	}


	public int getTurnCount()
	{
		return turnCount;
	}

	public static Random getRandNum()
	{
		return randNum;
	}

	public Colony getColony()
	{
		return antColony;
	}

	public void takeTurns(ArrayList ants, Environment[][] board)
	{
		balaChance = getRandNum().nextInt(100) + 1;
		if (balaChance == 1 || balaChance == 50 || balaChance == 100)
		{
			getColony().addBala(ants, 0, 0);
		}

		for (int ant = 0; ant < ants.size(); ant++)
		{

			if (ants.get(ant) instanceof Forager)
			{
				foragerTurn((Forager)ants.get(ant), ants);
			}

			else if (ants.get(ant) instanceof Scout)
			{
				scoutTurn((Scout)ants.get(ant), ants, board);
			}

			else if (ants.get(ant) instanceof Queen)
			{
				queenTurn((Queen)ants.get(ant), ants, board, antColony);
			}

			else if (ants.get(ant) instanceof Soldier)
			{
				soldierTurn((Soldier)ants.get(ant), ants);
			}

			else if (ants.get(ant) instanceof Bala)
			{
				balaTurn((Bala)ants.get(ant), ants);
			}
		}

		//Update board after ants have taken turns and increment turn count
		if (turnCount % 10 == 0)
		{
			for (int row = 0; row < board.length; row ++)
			{
				for (int col = 0; col < board[row].length; col++)
				{
					board[row][col].decreasePheromones();
				}
			}
		}
		antColony.updateBoard();
		turnCount ++;
		setTime();
		newGame.setTime(getTime());
	}

	public void foragerTurn(Forager forager, ArrayList ants)
	{
		if (forager.getIsAlive())
		{
			if (forager.getCarryingFood())
			{
				forager.returnToNest(getColony().seeBoard());
			}

			else
			{
				forager.forage(getColony().seeBoard());
			}

			forager.incrementAge();
		}

		//If dead, remove from ant colony
		else
		{
			if (forager.getCarryingFood())
			{
				forager.removeFromEnvironment(getColony().seeBoard());
			}
			ants.remove(forager);
		}
	}

	public void scoutTurn(Scout scout, ArrayList ants, Environment[][] board)
	{
		//Check if scout is alive
		if (scout.getIsAlive())
		{
			scout.scouting(getColony().seeBoard());
			scout.incrementAge();
		}

		//If dead, remove from environment and remove from ant colony
		else
		{
			board[scout.getLocX()][scout.getLocY()].removeScouts();
			ants.remove(scout);
		}

	}

	public void queenTurn(Queen queen, ArrayList ants, Environment[][] board, Colony colony)
	{
		if (queen.getIsAlive())
		{
			//If food is present, eat 1 unit
			if (board[queen.getLocX()][queen.getLocY()].getNumOfFood() > 0)
			{
				board[queen.getLocX()][queen.getLocY()].decreaseNumOfFood();

				//Every ten turns (ie 1 day) hatch a new ant
				if (getTurnCount() % 10 == 0)
				{
					queen.hatchNewAnt(ants, board, colony);
				}

				queen.incrementAge();
			}

			//If queen's cell has no food, she dies - terminate simulation
			else
			{
				queen.setDeath();
				board[queen.getLocX()][queen.getLocY()].removeQueen();
				continueSim = false;
			}
		}

		//If queen is dead, terminate the simulation
		else
		{
			continueSim = false;
		}
	}

	public void soldierTurn(Soldier soldier, ArrayList ants)
	{
		boolean enemyHit = false;

		if (soldier.getIsAlive())
		{
			if (soldier.detectEnemy(getColony().seeBoard()))
			{
				enemyHit = soldier.attackEnemy(soldier.getAttackChance());

				if (enemyHit)
				{
					soldier.killEnemy(getColony().seeBoard(), getColony().seeColony());
				}
			}

			else
			{
				soldier.scoutForEnemy(getColony().seeBoard());
			}

			soldier.incrementAge();
		}

		else
		{
			soldier.removeFromBoard(getColony().seeBoard());
			ants.remove(soldier);

		}
	}

	public void balaTurn(Bala bala, ArrayList ants)
	{
		boolean enemyHit = false;

		if (bala.getIsAlive())
		{
			if (bala.detectEnemy(getColony().seeBoard()))
			{
				enemyHit = bala.attackEnemy(bala.getAttackChance());
				if (enemyHit)
				{
					bala.killEnemy(getColony().seeBoard(), getColony().seeColony());
				}

			}
			else
			{
				bala.moveAround(getColony().seeBoard());
			}

			bala.incrementAge();
		}

		else
		{
			bala.removeFromBoard(getColony().seeBoard());
			ants.remove(bala);
		}
	}

	public void setDays()
	{
		days = getTurnCount() / 10;
	}

	public int getDays()
	{
		return days;
	}

	public void setTime()
	{
		setDays();

		time = "Day " + getDays() + " Turn " + getTurnCount();
	}

	public String getTime()
	{
		return time;
	}

}
