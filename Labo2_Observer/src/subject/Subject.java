/**
 * Subject that has to be listened to
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

    /**
     * List of observers
     */
    LinkedList<Observer> observers = new LinkedList<>();

    /**
     * start listening to the subject,Attach a observer to the subject
     * @param o observer to attach
     */
    public void attach(ChronoPanel o){
        if(!observers.contains(o))
            observers.add(o);
    }

    /**
     * Stop listening to the subject, remove it from the list of the observers
     * @param o
     */
    public void detach(Observer o){
        observers.remove(o);
    }

    /**
     * Notify all subscribed obvservers when this function is called
     * mainly when the data of the subject has changed
     */
    public void notifie(){
        for(Observer o : observers){
            o.update();
        }
    }
}
