/**
 * Concrete class observer from the Observer pattern
 * It represents the GUI specific for the display
 * of an analog chronometer with an roman dial
 *
 * This class extends AnalogChrono which extends ChronoPanel
 * which extends JPanel and implements Observer
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
package observer.chronoGUI;

import subject.Chrono;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

public class RomanChrono extends AnalogChrono{

    // Image used to fetch the background image
    private Image img;

    // Pathfile to the background image
    private final static String IMG_FILEPATH = "assets/cadran_chiffres_romains.jpg";

    /**
     * Constructor getting the background image from the assets folder
     *
     * @param chrono the Chrono subject to observe
     * */
    public RomanChrono(Chrono chrono) {
        super(chrono);
        img = Toolkit.getDefaultToolkit().getImage(IMG_FILEPATH).getScaledInstance(getPreferredSize().width,
                getPreferredSize().height, Image.SCALE_DEFAULT);
    }

    /**
     * paintComponent method from JComponent overriden
     * to draw the background image, the text (name + value) of the chronometer
     * and the pointers
     *
     * @param g Graphics of the JPanel used to draw on
     * */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        drawPointersAndLabel(g);
    }

    /**
     * Definition of the update method from the Observer interface
     * This method is called when the subject chrono notify its observers
     * It updates the display of this arab chronometer GUI
     * */
    @Override
    public void update() {
        paintComponent(getGraphics());

    }
}
