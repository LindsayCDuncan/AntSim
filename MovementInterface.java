package antsimulator;

//** An interface for movement */

public interface MovementInterface
{

	/** Move north
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveNorth(int locX, int locY, Environment[][] board);


	/** Move South
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveSouth(int locX, int locY, Environment[][] board);


	/** Move East
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveEast(int locX, int locY, Environment[][] board);


	/** Move West
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveWest(int locX, int locY, Environment[][] board);


	/** Move North-West
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveNW(int locX, int locY, Environment[][] board);


	/** Move South-West
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveSW(int locX, int locY, Environment[][] board);


	/** Move North-East
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveNE(int locX, int locY, Environment[][] board);


	/**Move South-East
	 *
	 * @param locX  Current location X.
	 * @param locY  Current location Y.
	 */
	public void moveSE(int locX, int locY, Environment[][] board);


	/** Generic move - moves to the indicated location
	 *
	 * @param locX  NEW location x.
	 * @param locY  NEW location y.
	 */
	public void move(int locX, int locY);
}
