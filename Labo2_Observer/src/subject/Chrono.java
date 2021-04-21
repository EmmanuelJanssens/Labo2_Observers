/**
 * Main subject of the work
 * Basic timer that increments every 1000 ms
 *
 * Has basi action for a chronometer such as
 *  start
 *  stop
 *  reset
 */
package subject;

import java.util.Timer;
import java.util.TimerTask;

public class Chrono extends Subject {

    long seconds = 0;
    boolean isRunning = false;
    private Timer timer;

    /**
     * Contructor creates a new timer
     */
    public Chrono(){


        // Toute les secondes, on augmente d'une seconde et on notifie les observeurs
        timer = new Timer();

    }

    /**
     * switch between states mainly to stop or start a timer
     */
    public void switchState() {
        if(isRunning)
            stop();
        else
            start();
    }

    /**
     * starts a timer, uses the Timertask to run continuously at a fixed rate
     */
    public void start() {
        if(!isRunning)
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Chrono.this.setSeconds(Chrono.this.getSeconds() + 1);
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
     * Reset the timers
     * Stop TimerTask and set seconds to 0
     */
    public void reset() {
        stop();
        setSeconds(0);
    }

    /**
     * Mutator for seconds, changes the data of the chronometer
     * so we must notify all observers
     * @param s
     */
    public void setSeconds(long s)
    {
        seconds = s;
        notifie();
    }

    /**
     * Formats data to seconds
     * @return
     */
    public long getSeconds()
    {
        return (seconds%3600) % 60;
    }

    /**
     * formats data to minutes
     * @return minutes
     */
    public long getMinutes()
    {
        return (seconds%3600) / 60;
    }

    /**
     * formats data to hours
     * @return hours
     */
    public long getHours()
    {
        return seconds/3600;
    }

    /**
     * to check if the timer is running
     * @return
     */
    public boolean IsRunning()
    {
        return isRunning;
    }
}
