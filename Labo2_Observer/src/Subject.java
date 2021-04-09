import java.util.ArrayList;
import java.util.*;

public abstract class Subject {

    List<Observer> observers = new ArrayList<>();

    public void attach(Observer o){
        observers.add(o);
    }    

    public void detach(Observer obs){
        for(Observer o : observers)
        {
            if(obs.equals(o)){
                observers.remove(o);
                break;
            }
        }
    }

    public void Notify(){
        for(Observer o : observers){
            o.update();
        }
    }
}
