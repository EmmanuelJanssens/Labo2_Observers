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

        // Thickness of the the pointer's stroke line
        private int weight;

        // Color of the pointer
        private Color color;

        /**
         * Constructor
         * */
        Pointer(int pointerEndX, int pointerEndY, int w, Color c) {
            this.pointerEndX = pointerEndX;
            this.pointerEndY = pointerEndY;
            weight = w;
            color = c;
        }
    }

    // One of the coordinates of the center of the (square) panel, used to position elements
    private final int PANEL_CENTER = (int) getPreferredSize().getWidth()/2;

    // The 3 pointers of an analog chronometer
    private final Pointer SECONDS = new Pointer(PANEL_CENTER, 20,1,Color.red);
    private final Pointer MINUTES = new Pointer(PANEL_CENTER, 50,3,Color.blue);
    private final Pointer HOURS = new Pointer(PANEL_CENTER, 70,5,Color.black);

    // The position of the chronometer's label name to display
    private final int LABEL_POS_X = PANEL_CENTER - 25;
    private final int LABEL_POS_Y = PANEL_CENTER + 15;

    /**
     * Constructor
     *
     * @param chrono the Chrono subject to observe
     * @param name the chronometer's label name to display
     * */
    protected AnalogChrono(Chrono chrono, String name){
        super(chrono,name);
    }

    /**
     * Draw all the elements on the analog chronometer
     *
     * @param g the Graphics of the panel where is drawn the elements
     * */
    protected void drawPointersAndLabel(Graphics g){

        // Draw the chronometer's label name
        g.drawString(getLabelName(), LABEL_POS_X, LABEL_POS_Y);

        // Draw the pointers
        drawPointer((Graphics2D) g, getChrono().getSeconds(), SECONDS.weight, SECONDS.color,
                SECONDS.pointerEndX, SECONDS.pointerEndY);
        drawPointer((Graphics2D) g, getChrono().getMinutes(), MINUTES.weight, MINUTES.color,
                MINUTES.pointerEndX, MINUTES.pointerEndY);
        drawPointer((Graphics2D) g, getChrono().getHours() * 5, HOURS.weight, HOURS.color,
                HOURS.pointerEndX, HOURS.pointerEndY);
    }

    /**
     * Draw a pointer
     *
     * @param g2d the Graphics2D of the panel where is drawn the elements
     * @param chronoValue the number of seconds/minutes/hours spent by the subject chronometer running
     * @param pointerColor the color of teh pointer
     * @param pointerEndX the pointer's end x position
     * @param pointerEndY the pointer's end y position
     * @param pointerWeight the thickness of the pointer's stroke line
     * */
    private void drawPointer(Graphics2D g2d, long chronoValue, float pointerWeight, Color pointerColor,
                               float pointerEndX, float pointerEndY){

        g2d.setStroke(new BasicStroke(pointerWeight));
        g2d.setColor(pointerColor);

        Line2D pointerLine = new Line2D.Float(PANEL_CENTER, PANEL_CENTER, pointerEndX, pointerEndY);
        AffineTransform atSecond =
                AffineTransform.getRotateInstance(
                        Math.toRadians(6 * chronoValue),
                        pointerLine.getX1(), pointerLine.getY1());
        g2d.draw(atSecond.createTransformedShape(pointerLine));
    }
}
