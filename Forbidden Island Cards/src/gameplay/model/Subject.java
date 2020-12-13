package gameplay.model;
import gameplay.control.Observer;

public interface Subject {
    public void attach(Observer o);
    public void detach(Observer o);
    public void notifyUpdate();
}
