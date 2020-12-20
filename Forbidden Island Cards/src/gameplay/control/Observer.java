package gameplay.control;
/**
 * Observer interface for implementing game win and loss conditions.
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public interface Observer 
{
	/**
	 * Function allowing those to be observed to update observer of a change in state
     * @param int method allows for selection of a particular method of update
	 */
    public void update(int m);
}