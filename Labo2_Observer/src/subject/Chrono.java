/**
 * This class represents the concrete subject of a chronometer
 * from the Observer pattern
 *
 * It contains a basic timer that increments every 1000 ms
 * and has basic actions for a chronometer such as
 *  - start
 *  - stop
 *  - reset
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
package subject;

import java.util.Timer;
import java.util.TimerTask;

public class Chrono extends Subject {

    // Represents the data of the concrete subject :
    // the total number of seconds spent by the chronometer running
    private int seconds = 0;

    // The Chrono subject id updated by the counter
    private int id;
    private static int counter;

    private Timer timer;
    private boolean isRunning = false;

    /**
     * Contructor creates a new timer
     */
    public Chrono(){
        id = ++counter;
        timer = new Timer();
    }

    /**
     * Switch between states mainly to stop or start a timer
     */
    public void switchState() {
        if(isRunning)
            stop();
        else
            start();
    }

    /**
     * Starts a timer, uses the Timertask to run continuously at a fixed rate
     */
    public void start() {
        if(!isRunning)
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Chrono.this.setSecondsData(Chrono.this.seconds + 1);
                }
            },0,1000);
        isRunning = true;
    }

    /**
     * Stop the timer
     */
    public void stop() {
        if(isRunning)
        {
            timer.cancel();
            timer = new Timer();
            isRunning = false;
        }
    }

    /**
     * Reset the timer
     * Stop TimerTask and set seconds to 0
     */
    public void reset() {
        stop();
        setSecondsData(0);
    }

    /**
     * Mutator for seconds, changes the data of the chronometer
     * so we must notify all observers
     * @param s the new value for seconds
     */
    private void setSecondsData(int s) {
        seconds = s;
        notifie();
    }

    /**
     * Getter for the data seconds
     *
     * @return an int the total number of seconds spent by the chronometer running
     */
    public int getSecondsData()
    {
        return seconds;
    }

    /**
     * Getter for the subject id
     *
     * @return an int the chronometer's id
     * */
    public int getId(){
        return id;
    }

}
