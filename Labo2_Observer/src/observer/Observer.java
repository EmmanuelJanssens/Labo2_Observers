/**
 * Observer interface from the Observer pattern
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
package observer;

public interface Observer{

    /**
     * Implemented by all the concrete observers
     * to change their state depending on the subject data observed
     * */
    void update();
}
