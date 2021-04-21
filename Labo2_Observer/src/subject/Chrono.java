
package subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Chrono extends Subject {

    long seconds = 0;
    boolean isRunning = false;
    private Timer timer;
    
    public Chrono(){


        // Toute les secondes, on augmente d'une seconde et on notifie les observeurs
        timer = new Timer();

    }

    public void switchState()
    {
        if(isRunning)
            stop();
        else
            start();
    }
    public void start()
    {
        if(!isRunning)
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Chrono.this.setSeconds(Chrono.this.getSeconds() + 1);
                }
            },0,1000);
        isRunning = true;
    }
    public void stop()
    {
        if(isRunning)
        {
            timer.cancel();
            timer = new Timer();
            isRunning = false;
        }
    }

    public void reset()
    {
        if(isRunning)
        {
            stop();
            setSeconds(0);
        }
    }
    public void setSeconds(long s)
    {
        seconds = s;
        notifie();
    }
    public long getSeconds()
    {
        return (seconds%3600) % 60;
    }

    public long getMinutes()
    {
        return (seconds%3600) / 60;
    }
    public long getHours()
    {
        return seconds/3600;
    }

    public boolean IsRunning()
    {
        return isRunning;
    }
}
