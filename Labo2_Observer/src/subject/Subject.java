/**
 * Abstract subject class from the Observer pattern
 * It contains the basic implementations of a subject:
 * attach, detach and notify an observer methods
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
package subject;

import observer.chronoGUI.ChronoPanel;
import observer.Observer;

import java.util.LinkedList;

abstract public class Subject {

    // List of observers attached to the subject
    private final LinkedList<Observer> observers = new LinkedList<>();

    /**
     * Attach an observer to the subject
     * The observer starts to wait for notification from the subject
     *
     * @param o observer to attach
     */
    public void attach(ChronoPanel o){
        if(!observers.contains(o))
            observers.add(o);
    }

    /**
     * Remove an observer from the list of observers
     * The subject stop to notify the observer
     *
     * @param o observer to remove
     */
    public void detach(Observer o){
        observers.remove(o);
    }

    /**
     * Notify all subscribed observers
     * This method is called when the data of the subject has changed
     */
    void notifie(){
        for(Observer o : observers){
            o.update();
        }
    }
}
