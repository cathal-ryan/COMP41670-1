package gameplay.model;
import gameplay.control.Observer;

public interface Subject {
    public void notifyUpdate(Observer o, int m);
}
