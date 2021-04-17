
package subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Chrono extends Subject {

    private ChronoData chronoData;

    private Timer timer;
    
    public Chrono(){

        chronoData = new ChronoData();

        // Toute les secondes, on augmente d'une seconde et on notifie les observeurs
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chronoData.addOneSecond(); // "play"
                notifie();
            }
        });


    }

    public ChronoData getChronoData() {
        return chronoData;
    }

    public void setChronoData(State state) {
        this.chronoData.setCurrentState(state);

        switch (state){
            case RUNNING:
                timer.start();
                break;
            case PAUSED:
                timer.stop();
                notifie();
                break;
            case RESET:
                this.chronoData = new ChronoData();
                timer.stop();
                notifie();
                break;
            default:
                break;
        }

    }
}
