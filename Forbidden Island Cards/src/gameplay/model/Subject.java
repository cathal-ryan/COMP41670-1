package gameplay.model;
import gameplay.control.Observer;
/**
 * Subject interface for implementing an observable for observer pattern.
 * Earlier releases had several subjects, until this was encapsulated into GameModel.
 * Currently only instance implementing this interface, unless refactored at a later date.
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
