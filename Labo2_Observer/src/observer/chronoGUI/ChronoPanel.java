/**
 * Abstract class implementing the Observer interface
 * from the Observer pattern
 *
 * It represents a JPanel specific for a chronometer GUI
 * observing a Chrono subject, retrieving data from it
 * and formatting those datas for concretes observers
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
package observer.chronoGUI;

import observer.Observer;
import subject.Chrono;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract public class ChronoPanel extends JPanel implements Observer {

    // Concrete subject to observe
    private Chrono chrono;

    // Dimensions of the panel
    private final static int WIDTH = 200;
    private final static int HEIGHT = 200;


    /**
     * Constructor create a JPanel with the subject to observe, subscribe to it,
     * set size and add a mouse listener
     *
     * @param chrono the Chrono subject to observe
     * */
    protected ChronoPanel(Chrono chrono){

        this.chrono = chrono;

        // When an observer is created,
        // it subscribes the current observer to a subject
        chrono.attach(this);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // When the user clicks on the panel of a chronometer,
        // it stop the timer if it was running and vice-versa
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                chrono.switchState();
            }
        });
    }

    /**
     * Formats data to seconds for a chronometer
     *
     * @return minutes
     */
    protected int getSeconds(){
        return (chrono.getSecondsData()%3600) % 60;
    }

    /**
     * Formats data to minutes for a chronometer
     *
     * @return minutes
     */
    protected int getMinutes()
    {
        return (chrono.getSecondsData()%3600) / 60;
    }

    /**
     * Formats data to hours for a chronometer
     *
     * @return hours
     */
    protected int getHours()
    {
        return chrono.getSecondsData()/3600;
    }

    /**
     * Getter for the text to display around the middle of the chronometer GUI
     *
     * @return a String the text to display
     **/
    protected String getText() {
        return "Chrono #" + chrono.getId();
    }

    /**
     * Closing a window containing observer detach the observers from the subject
     * stopping the subject from continuing to notify a deleted observer
     * */
    public void setFrameListener() {
        JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        parent.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                chrono.detach(ChronoPanel.this);
            }
        });
    }
}
