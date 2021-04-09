import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Chrono extends Subject {

    private ChronoData chronoState;

    private Timer timer;

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

        timer.start();

    }

    public ChronoData getChronoState() {
        return chronoState;
    }

    //reçoit l'info si on ppuye sur pause à travers l'observeur
    public void setChronoState(ChronoData chronoState) {
        //TODO
        this.chronoState = chronoState;
    }
}
