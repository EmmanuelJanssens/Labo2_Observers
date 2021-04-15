
package subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Chrono extends Subject {

    private ChronoData chronoState;

    private Timer timer;

    boolean paused = false;
    boolean running = false;
    
    public Chrono(){

        chronoState = new ChronoData();

        // Toute les secondes, on augmente d'une seconde et on notifie les observeurs
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chronoState.addOneSecond(); // "play"
                notifie();
            }
        });


    }
    public void start()
    {
        timer.start();
    }
    public void reset()
    {
        this.chronoState = new ChronoData();
        timer.stop();
        notifie();
    }
    public void pause()
    {
        timer.stop();
        notifie();
    }
    public ChronoData getChronoState() {
        return chronoState;
    }

    //reçoit l'info si on appuye sur pause à travers l'observeur
    public void setChronoState(ChronoData chronoState) {
        //TODO
        this.chronoState = chronoState;
    }
}
