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
    // the total seconds spent by the chronometer running
    private long seconds = 0;


    private boolean isRunning = false;
    private Timer timer;

    /**
     * Contructor creates a new timer
     */
    public Chrono(){

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
                    Chrono.this.setSeconds(Chrono.this.seconds + 1);
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
        setSeconds(0);
    }

    /**
     * Mutator for seconds, changes the data of the chronometer
     * so we must notify all observers
     * @param s the new value for seconds
     */
    private void setSeconds(long s) {
        seconds = s;
        notifie();
    }

    /**
     * Formats data to seconds for a chronometer
     * @return seconds
     */
    public long getSeconds()
    {
        return (seconds%3600) % 60;
    }

    /**
     * Formats data to minutes for a chronometer
     * @return minutes
     */
    public long getMinutes()
    {
        return (seconds%3600) / 60;
    }

    /**
     * formats data to hours for a chronometer
     * @return hours
     */
    public long getHours()
    {
        return seconds/3600;
    }

}
