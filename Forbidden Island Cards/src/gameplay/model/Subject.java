package gameplay.model;
import gameplay.control.Observer;
import enums.EndGameEnums;

public interface Subject {
    public void notifyUpdate(Observer o, int m);
}
