/**
 * Abstract class implementing the Observer interface
 * from the Observer pattern
 *
 * It represents a JPanel specific for a chronometer GUI
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

    // Represents label displayed on the middle of the panel (Chrono#id)
    private String labelName;

    // Dimension of the panel
    private final Dimension dimension = new Dimension(200,200);

    /**
     * Constructor
     *
     * @param chrono the Chrono subject to observe
     * @param labelName the label name of the chronometer to display
     * */
    protected ChronoPanel(Chrono chrono, String labelName){
        this.labelName = labelName;

        setPreferredSize(new Dimension(200,200));

        // When the user clicks on the panel of a chronometer,
        // it stop the timer if it was running and vice-versa
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                chrono.switchState();
            }
        });

        this.chrono = chrono;

        // When an observer is created,
        // it subscribes the current observer to a subject
        chrono.attach(this);
    }

    /**
     * Getter for the subject chrono
     *
     * @return a Chrono the subject attached to the current observer
     * */
    protected Chrono getChrono() {
        return chrono;
    }

    /**
     * Getter for the label to display
     *
     * @return a String the label name
     * */
    protected String getLabelName() {
        return labelName;
    }

    /**
     * Closing a window containing observers
     * detach the observers from the subject
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
