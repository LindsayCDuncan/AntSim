package antsimulator;

/**An attack interface*/

public interface Attackable
{
	/** Detect if an enemy is nearby.
	 *
	 * @return True if an enemy is there. False if otherwise
	 */
	public boolean detectEnemy(Environment[][] board);


	/** Attack an enemy

	 */
	public boolean attackEnemy(int attackChance);

	public void killEnemy(Environment[][] board, ArrayList ants);

}
