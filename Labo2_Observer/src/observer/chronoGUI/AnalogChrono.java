/**
 * Abstract class observer from the Observer pattern
 * It represents the GUI specific for a chronometer with an analog dial
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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

abstract class AnalogChrono extends ChronoPanel {

    /**
     * Private class representing a graphical pointer
     * on an analog dial chronometer
     * */
    private class Pointer {

        // Pointer end coordinates position
        private int pointerEndX;
        private int pointerEndY;

        // Thickness of the pointer line stroke
        private int weight;

        // Color of the pointer
        private Color color;

        /**
         * Constructor create a pointer
         *
         * @param endX pointer end x position
         * @param endY pointer end y position
         * @param c Color of the pointer
         * @param w Thickness of the pointer line stroke
         * */
        private Pointer(int endX, int endY, int w, Color c) {
            this.pointerEndX = endX;
            this.pointerEndY = endY;
            this.weight = w;
            this.color = c;
        }
    }

    // One of the coordinates of the center of the (square) panel,
    // used in multiple times to position elements
    private final int PANEL_CENTER = (int) getPreferredSize().getWidth()/2;

    // The 3 pointers of an analog chronometer
    private final Pointer SECONDS = new Pointer(PANEL_CENTER, 20,1,Color.red);
    private final Pointer MINUTES = new Pointer(PANEL_CENTER, 50,3,Color.blue);
    private final Pointer HOURS = new Pointer(PANEL_CENTER, 70,5,Color.black);

    // The position of the chronometer text ("Chrono#id") to display
    private final int LABEL_POS_X = PANEL_CENTER - 25;
    private final int LABEL_POS_Y = PANEL_CENTER + 15;

    /**
     * Constructor
     *
     * @param chrono the Chrono subject to observe
     * */
    protected AnalogChrono(Chrono chrono){
        super(chrono);
    }

    /**
     * Draw all the elements on the analog chronometer
     *
     * @param g the Graphics of the panel where is drawn the elements
     * */
    protected void drawPointersAndText(Graphics g){

        // Draw the chronometer text on GUI (Chrono#id)
        g.drawString(getText(), LABEL_POS_X, LABEL_POS_Y);

        // Draw the pointers
        drawPointer((Graphics2D) g, getSeconds(), SECONDS);
        drawPointer((Graphics2D) g, getMinutes(), MINUTES);
        // The hour pointer moves with an angle 5 times greater than the others
        drawPointer((Graphics2D) g, getHours() * 5, HOURS);
    }

    /**
     * Draw a pointer depending on the chronometer time spent
     *
     * @param g2d the Graphics2D of the panel where is drawn the elements
     * @param chronoValue the number of seconds/minutes/hours spent by the subject chronometer running
     * @param pointer the Pointer to draw
     * */
    private void drawPointer(Graphics2D g2d, int chronoValue, Pointer pointer){

        g2d.setStroke(new BasicStroke(pointer.weight));
        g2d.setColor(pointer.color);

        Line2D pointerLine = new Line2D.Float(PANEL_CENTER, PANEL_CENTER, pointer.pointerEndX, pointer.pointerEndY);
        AffineTransform atSecond =
                AffineTransform.getRotateInstance(
                        Math.toRadians(6 * chronoValue),
                        pointerLine.getX1(), pointerLine.getY1());
        g2d.draw(atSecond.createTransformedShape(pointerLine));
    }
}
