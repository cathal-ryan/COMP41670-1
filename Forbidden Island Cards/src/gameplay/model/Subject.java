package gameplay.model;
import gameplay.control.Observer;
/**
 * Subject interface for implementing an observable for observer pattern.
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public interface Subject {
	/**
	 * Function allowing subject to notify particular observer of change in state
     * @param o The observer in question
     * @param m Integer selection of method for update
	 */
    public void notifyUpdate(Observer o, int m);
}
