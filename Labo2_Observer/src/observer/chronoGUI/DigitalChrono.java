/**
 * Concrete class observer from the Observer pattern
 * It represents the GUI specific for the display of a digital chronometer
 *
 * This class extends ChronoPanel which extends JPanel and implements Observer
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
package observer.chronoGUI;

import subject.Chrono;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;

public class DigitalChrono extends ChronoPanel {

    // Label used to display the changing time on the digital chronometer
    private JLabel timeLabel;

    // Format String to display digits
    private final DecimalFormat TIME_FORMAT = new DecimalFormat("00");

    /**
     * Constructor
     *
     * @param chrono the Chrono subject to observe
     * @param name the label name of the chronometer to display
     */
    public DigitalChrono(Chrono chrono, String name){

        super(chrono,name);
        setLayout(new GridBagLayout());

        // Add the chronometer's label name on the panel
        add(new JLabel(name + ": "));

        // Initialize the label used to display the time
        timeLabel = new JLabel();
        setTimeLabel(0,0,0);
        add(timeLabel);
    }

    /**
     * Definition of the update method from the Observer interface
     * This method is called when the subject chrono notify its observers
     * It updates the display of this digital chronometer GUI
     * */
    @Override
    public void update() {
        setTimeLabel(getChrono().getHours(), getChrono().getMinutes(), getChrono().getSeconds());
    }

    /**
     * Set the time displayed on the label responsible of this
     *
     * @param hour number of hour to set
     * @param minute number of minutes to set
     * @param second number of seconds to set
     * */
    private void setTimeLabel(long hour, long minute, long second){
        timeLabel.setText(TIME_FORMAT.format(hour) + "h "
                + TIME_FORMAT.format(minute) + "m "
                + TIME_FORMAT.format(second) + "s ");
    }
}
