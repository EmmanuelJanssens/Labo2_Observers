package subject;

import observer.Observer;

import java.util.LinkedList;

abstract public class Subject {

    LinkedList<Observer> observers = new LinkedList<>();

    public void attach(Observer o){
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
