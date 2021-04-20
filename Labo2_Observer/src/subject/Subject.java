package subject;

import observer.chronoGUI.ChronoPanel;
import observer.Observer;

import java.util.LinkedList;

abstract public class Subject {

    LinkedList<Observer> observers = new LinkedList<>();

    public void attach(ChronoPanel o){
        observers.add(o);
    }

    public void detach(Observer o){
        observers.remove(o);
    }

    public void notifie(){
        for(Observer o : observers){
            o.update();
        }
    }
}
