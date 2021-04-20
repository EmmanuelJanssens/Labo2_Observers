
package subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.Timer;

public class Chrono extends Subject {

    long seconds = 0;
    private class Increment extends TimerTask{
        @Override
        public void run(){
            Chrono.this.setSeconds(seconds +1);
        }
    }
    private Timer timer;
    
    public Chrono(){


        // Toute les secondes, on augmente d'une seconde et on notifie les observeurs
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                notifie();
            }
        });


    }
    public void setSeconds(long s)
    {
        seconds = s;
        notifie();
    }
    public ChronoData getChronoData() {
        return null;
    }

    public void setChronoData(State state) {

    }
}
